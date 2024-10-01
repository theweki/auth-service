package dev.weki.auth_service.controller;

import dev.weki.auth_service.utility.GenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HomeController {

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public GenericResponse<Map<String, String>> home() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "success");
        return new GenericResponse<>(
                HttpStatus.OK.value(),
                "Server Is Running...",
                response
        );
    }

}
