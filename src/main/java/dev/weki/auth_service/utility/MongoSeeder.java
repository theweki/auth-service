package dev.weki.auth_service.utility;

import dev.weki.auth_service.model.Role;
import dev.weki.auth_service.model.UserEntity;
import dev.weki.auth_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

// @Configuration
@RequiredArgsConstructor
public class MongoSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        userRepository.deleteAll();

        UserEntity admin = UserEntity.builder()
                .name("admin")
                .password(passwordEncoder.encode("password"))
                .contact("1111111110")
                .email("admin@gmail.com")
                .role(Role.ADMIN)
                .build();

        UserEntity user = UserEntity.builder()
                .name("user")
                .password(passwordEncoder.encode("password"))
                .contact("1111111111")
                .email("user@gmail.com")
                .build();

        UserEntity alpha = UserEntity.builder()
                .name("alpha")
                .password(passwordEncoder.encode("password"))
                .contact("1111111112")
                .email("alpha@gmail.com")
                .build();

        UserEntity beta = UserEntity.builder()
                .name("beta")
                .password(passwordEncoder.encode("password"))
                .contact("1111111113")
                .email("beta@gmail.com")
                .build();

        UserEntity gamma = UserEntity.builder()
                .name("gamma")
                .password(passwordEncoder.encode("password"))
                .contact("1111111114")
                .email("gamma@gmail.com")
                .build();

        userRepository.saveAll(List.of(admin, user, alpha, beta, gamma));
    }

}
