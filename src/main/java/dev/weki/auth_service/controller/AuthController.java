package dev.weki.auth_service.controller;

import dev.weki.auth_service.dto.LoginDto;
import dev.weki.auth_service.dto.RegisterDto;
import dev.weki.auth_service.dto.TokenRecord;
import dev.weki.auth_service.dto.UserRecord;
import dev.weki.auth_service.service.AuthService;
import dev.weki.auth_service.utility.GenericResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public GenericResponse<UserRecord> register(@Valid @RequestBody RegisterDto registerDto) {
        return new GenericResponse<>(
                HttpStatus.CREATED.value(),
                "User Registered Successfully",
                authService.register(registerDto)
        );
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public GenericResponse<TokenRecord> login(@Valid @RequestBody LoginDto loginDto) {
        return new GenericResponse<>(
                HttpStatus.OK.value(),
                "User Logged In Successfully",
                authService.login(loginDto)
        );
    }

}
