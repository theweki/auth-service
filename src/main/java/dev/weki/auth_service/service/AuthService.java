package dev.weki.auth_service.service;

import dev.weki.auth_service.dto.LoginDto;
import dev.weki.auth_service.dto.RegisterDto;
import dev.weki.auth_service.dto.TokenRecord;
import dev.weki.auth_service.dto.UserRecord;
import dev.weki.auth_service.exception.UserAlreadyExistsException;
import dev.weki.auth_service.mapper.UserMapper;
import dev.weki.auth_service.model.UserEntity;
import dev.weki.auth_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    private final TokenService tokenService;
    private final UserMapper userMapper;

    public UserRecord register(RegisterDto registerDto) {
        if (userRepository.existsByEmail(registerDto.getEmail())) {
            throw new UserAlreadyExistsException("User already exists");
        }

        UserEntity userEntity = UserEntity.builder()
                .name(registerDto.getName())
                .contact(registerDto.getContact())
                .email(registerDto.getEmail())
                .password(passwordEncoder.encode(registerDto.getPassword()))
                .build();

        UserEntity savedUser = userRepository.save(userEntity);
        return userMapper.toUserRecord(savedUser);
    }

    public TokenRecord login(LoginDto loginDto) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                loginDto.getEmail(),
                loginDto.getPassword()
        );

        Authentication authentication = authenticationManager.authenticate(token);
        String accessToken = tokenService.generateToken(authentication);

        UserEntity user = userRepository
                .findByEmail(authentication.getName())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new TokenRecord(userMapper.toUserRecord(user), "Bearer", accessToken);
    }
}
