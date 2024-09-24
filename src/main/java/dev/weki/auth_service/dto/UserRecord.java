package dev.weki.auth_service.dto;

import dev.weki.auth_service.model.Role;

import java.util.List;

public record UserRecord(
        String id,
        String name,
        String email,
        String contact,
        Role role,
        List<String> friends,
        List<String> groups
) {
}
