package dev.weki.auth_service;

import dev.weki.auth_service.security.RSAKeyProperties;
import dev.weki.auth_service.model.Role;
import dev.weki.auth_service.model.UserEntity;
import dev.weki.auth_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@RequiredArgsConstructor
@EnableConfigurationProperties(RSAKeyProperties.class)
public class AuthServiceApplication {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(AuthServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner() {
    	return _ -> {

			UserEntity admin = UserEntity.builder()
					.name("admin")
					.password(passwordEncoder.encode("password"))
					.contact("9898767876")
					.email("admin@gmail.com")
					.role(Role.ADMIN)
					.build();

			UserEntity user = UserEntity.builder()
					.name("user")
					.password(passwordEncoder.encode("password"))
					.contact("9898767876")
					.email("user@gmail.com")
					.build();

			userRepository.deleteAll();
			userRepository.save(admin);
			userRepository.save(user);
		};
	}
}
