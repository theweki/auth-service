package dev.weki.auth_service.exception;

import dev.weki.auth_service.utility.GenericError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<GenericError<String>> handleUserAlreadyExistsException(UserAlreadyExistsException e) {
        return genericError(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(UserDoesNotExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<GenericError<String>> handleUserDoesNotExistsException(UserDoesNotExistsException e) {
        return genericError(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(NoResourceFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<GenericError<String>> handleNoResourceFoundException(NoResourceFoundException e) {
        return genericError(HttpStatus.NOT_FOUND, e.getMessage());
    }

    /* VALIDATION */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<GenericError<Map<String, String>>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        Map<String, String> errorResponse = new HashMap<>();
        e.getFieldErrors().forEach(
                fieldError -> errorResponse.put(fieldError.getField(), fieldError.getDefaultMessage())
        );
        return genericError(HttpStatus.BAD_REQUEST, errorResponse);
    }

    /* SPRING SECURITY */
    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<GenericError<String>> handleAuthenticationException(AuthenticationException e) {
        return genericError(HttpStatus.UNAUTHORIZED, e.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<GenericError<String>> handleAccessDeniedException(AccessDeniedException e) {
        return genericError(HttpStatus.FORBIDDEN, e.getMessage());
    }

    /* OTHER EXCEPTIONS */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<GenericError<Map<String, String>>> handleException(Exception e) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("message", e.getMessage());
        errorResponse.put("exception_class", e.getClass().getName());
        return genericError(HttpStatus.INTERNAL_SERVER_ERROR, errorResponse);
    }

    /* UTILITY */
    private <T> ResponseEntity<GenericError<T>> genericError(HttpStatus statusCode, T data) {
        GenericError<T> errorResponse = new GenericError<>(statusCode.value(), data);
        log.error("Exception: {}", errorResponse);
        return ResponseEntity.status(statusCode).body(errorResponse);
    }
}
