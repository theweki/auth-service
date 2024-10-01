package dev.weki.auth_service.service;

import dev.weki.auth_service.dto.FriendDto;
import dev.weki.auth_service.dto.UserRecord;
import dev.weki.auth_service.exception.UserAlreadyExistsException;
import dev.weki.auth_service.exception.UserDoesNotExistsException;
import dev.weki.auth_service.mapper.UserMapper;
import dev.weki.auth_service.model.UserEntity;
import dev.weki.auth_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    /* USER RELATED */
    public UserRecord getUser() {
        UserEntity user = getCurrentUserDetails();
        return userMapper.toUserRecord(user);
    }

    public FriendDto addFriend(FriendDto friendDto) {
        UserEntity user = getCurrentUserDetails();

        String friendEmail = friendDto.getEmail();
        if (!isUserExists(friendEmail)) {
            throw new UserDoesNotExistsException("Friend Not Found");
        }

        if (user.getFriends().contains(friendEmail)) {
            throw new UserAlreadyExistsException("Friend Already Added");
        }

        user.getFriends().add(friendEmail);
        userRepository.save(user);
        return friendDto;
    }

    public List<UserRecord> getFriends() {
        UserEntity user = getCurrentUserDetails();
        return user.getFriends()
                .stream()
                .map(this::getUserDetails)
                .map(userMapper::toUserRecord)
                .toList();
    }

    /* ADMIN RELATED */
    public List<UserRecord> getUsers() {
        return userRepository
                .findAll()
                .stream()
                .map(userMapper::toUserRecord)
                .toList();
    }

    /* UTILITIES */
    private UserEntity getCurrentUserDetails() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return getUserDetails(email);
    }

    private UserEntity getUserDetails(String email) {
        return userRepository
                .findByEmail(email)
                .orElseThrow(() -> new UserDoesNotExistsException("User Not Found"));
    }

    private Boolean isUserExists(String email) {
        return userRepository.existsByEmail(email);
    }

}
