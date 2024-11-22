package dev.weki.auth_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class FriendDto {
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid Email Format")
    private String email;
}
