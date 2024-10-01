package dev.weki.auth_service.controller;

import dev.weki.auth_service.dto.UserRecord;
import dev.weki.auth_service.service.UserService;
import dev.weki.auth_service.utility.GenericResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/users")
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public GenericResponse<List<UserRecord>> getUsers() {
        return new GenericResponse<>(
                HttpStatus.OK.value(),
                "Users Fetched Successfully",
                userService.getUsers()
        );
    }

}
