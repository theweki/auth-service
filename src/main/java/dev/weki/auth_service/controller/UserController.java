package dev.weki.auth_service.controller;

import dev.weki.auth_service.dto.FriendDto;
import dev.weki.auth_service.dto.UserRecord;
import dev.weki.auth_service.service.UserService;
import dev.weki.auth_service.utility.GenericResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/current")
    @ResponseStatus(HttpStatus.OK)
    public GenericResponse<UserRecord> getCurrentUserDetails() {
        return new GenericResponse<>(
                HttpStatus.OK.value(),
                "User Fetched Successfully",
                userService.getUser()
        );
    }

    @GetMapping("/friends")
    @ResponseStatus(HttpStatus.OK)
    public GenericResponse<List<UserRecord>> getFriends() {
        return new GenericResponse<>(
                HttpStatus.OK.value(),
                "User Friends Fetched Successfully",
                userService.getFriends()
        );
    }

    @PatchMapping("/friends")
    @ResponseStatus(HttpStatus.OK)
    public GenericResponse<FriendDto> addFriend(@Valid @RequestBody FriendDto friendDto) {
        return new GenericResponse<>(
                HttpStatus.OK.value(),
                "User Friend Added Successfully",
                userService.addFriend(friendDto)
        );
    }

}
