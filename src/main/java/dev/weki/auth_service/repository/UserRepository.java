package dev.weki.auth_service.repository;

import dev.weki.auth_service.model.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<UserEntity, String> {

    Optional<UserEntity> findByEmail(String email);

    Boolean existsByEmail(String email);
}
