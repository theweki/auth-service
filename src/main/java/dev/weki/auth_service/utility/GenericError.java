package dev.weki.auth_service.utility;

public record GenericError<T>(
        int status_code,
        T data
) {
}
