package dev.weki.auth_service.security;

import dev.weki.auth_service.exception.CustomAccessDeniedHandler;
import dev.weki.auth_service.exception.CustomAuthenticationEntryPoint;
import dev.weki.auth_service.service.MongoUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final MongoUserDetailsService mongoUserDetailsService;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .requiresChannel(rcc -> rcc.anyRequest().requiresInsecure()) // HTTP REQUESTS ONLY
                .cors(withDefaults())
                .csrf(AbstractHttpConfigurer::disable)

                .authorizeHttpRequests(rc -> {
                    rc.requestMatchers("/", "/auth/**").permitAll();
                    rc.requestMatchers("/users/**").hasAuthority("SCOPE_USER");
                    rc.requestMatchers("/admin/**").hasAuthority("SCOPE_ADMIN");
                    rc.anyRequest().authenticated();
                })

                .oauth2ResourceServer(oauth -> {
                    oauth.authenticationEntryPoint(customAuthenticationEntryPoint);
                    oauth.accessDeniedHandler(customAccessDeniedHandler);
                    oauth.jwt(withDefaults());
                })

                .exceptionHandling(ehc -> {
                    ehc.authenticationEntryPoint(customAuthenticationEntryPoint);
                    ehc.accessDeniedHandler(customAccessDeniedHandler);
                })

                .sessionManagement(smc -> smc.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true);
        configuration.addAllowedOrigin("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        daoAuthenticationProvider.setUserDetailsService(mongoUserDetailsService);
        return new ProviderManager(daoAuthenticationProvider);
    }
}
