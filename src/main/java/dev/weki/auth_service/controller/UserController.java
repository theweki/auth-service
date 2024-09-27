package dev.weki.auth_service.controller;

import dev.weki.auth_service.dto.UserRecord;
import dev.weki.auth_service.service.UserService;
import dev.weki.auth_service.utility.GenericResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/current")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public GenericResponse<UserRecord> getUserByToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return new GenericResponse<>(
                HttpStatus.OK.value(),
                "User Fetched Successfully",
                userService.getUserByEmail(authentication.getName())
        );
    }

}
