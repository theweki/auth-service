package dev.weki.auth_service.mapper;

import dev.weki.auth_service.dto.UserRecord;
import dev.weki.auth_service.model.UserEntity;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    public UserRecord toUserRecord(UserEntity userEntity) {
        return new UserRecord(
                userEntity.getId(),
                userEntity.getName(),
                userEntity.getEmail(),
                userEntity.getContact(),
                userEntity.getRole(),
                userEntity.getFriends(),
                userEntity.getGroups()
        );
    }
}
