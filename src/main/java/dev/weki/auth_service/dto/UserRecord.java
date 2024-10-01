package dev.weki.auth_service.dto;

public record UserRecord(
        String id,
        String name,
        String email,
        String contact
) {
}
