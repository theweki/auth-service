package dev.weki.auth_service.service;

import dev.weki.auth_service.dto.UserRecord;
import dev.weki.auth_service.mapper.UserMapper;
import dev.weki.auth_service.model.UserEntity;
import dev.weki.auth_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserRecord getUser() {
        UserEntity user = getCurrentUserDetails();
        return userMapper.toUserRecord(user);
    }

    public String addFriend(String email) {
        UserEntity user = getCurrentUserDetails();
        user.getFriends().add(email);
        userRepository.save(user);
        return email;
    }

    public List<UserRecord> getFriends() {
        UserEntity user = getCurrentUserDetails();
        return user.getFriends()
                .stream()
                .map(this::getUserDetails)
                .map(userMapper::toUserRecord)
                .toList();
    }

    private UserEntity getCurrentUserDetails() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return getUserDetails(email);
    }

    private UserEntity getUserDetails(String email) {
        return userRepository
                .findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

}
