package dev.weki.auth_service.dto;

public record TokenRecord(
        UserRecord user,
        String token_type,
        String access_token
) {
}
