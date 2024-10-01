package dev.weki.auth_service.controller;

import dev.weki.auth_service.dto.UserRecord;
import dev.weki.auth_service.service.UserService;
import dev.weki.auth_service.utility.GenericResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/current")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public GenericResponse<UserRecord> getCurrentUserDetails() {
        return new GenericResponse<>(
                HttpStatus.OK.value(),
                "User Fetched Successfully",
                userService.getUser()
        );
    }

    @GetMapping("/friends")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public GenericResponse<List<UserRecord>> getFriends() {
        return new GenericResponse<>(
                HttpStatus.OK.value(),
                "User Friends Fetched Successfully",
                userService.getFriends()
        );
    }

    @PatchMapping("/friends/{email}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public GenericResponse<String> addFriend(@PathVariable String email) {
        return new GenericResponse<>(
                HttpStatus.OK.value(),
                "User Friend Added Successfully",
                userService.addFriend(email)
        );
    }

}
