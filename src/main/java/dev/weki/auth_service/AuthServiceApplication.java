package dev.weki.auth_service;

import dev.weki.auth_service.security.RSAKeyProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@RequiredArgsConstructor
@EnableConfigurationProperties(RSAKeyProperties.class)
public class AuthServiceApplication {

	/* ADMIN AND USER DEPENDENCIES
	private final UserRepository _;
	private final PasswordEncoder _;
	*/

	public static void main(String[] args) {
		SpringApplication.run(AuthServiceApplication.class, args);
	}

	/* ADMIN AND USER
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
	*/
}
