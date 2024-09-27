package dev.weki.auth_service.dto;

public record TokenRecord(
        String token_type,
        String access_token
) {
}
