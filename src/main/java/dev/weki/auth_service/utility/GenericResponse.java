package dev.weki.auth_service.utility;

public record GenericResponse<T>(
        int status_code,
        String message,
        T data
) {
}