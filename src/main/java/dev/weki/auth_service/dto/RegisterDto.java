package dev.weki.auth_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterDto {
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid Email Format")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be minimum of 6 characters")
    private String password;

    @NotBlank(message = "Name is required")
    @Size(min = 2, message = "Name must be minimum of 2 characters")
    private String name;

    @Pattern(regexp = "^[0-9]{10}$", message = "Invalid Contact")
    private String contact;
}
