package com.devsuperior.movieflix.resources.exceptions;

import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;
import com.devsuperior.movieflix.services.exceptions.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> methodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        ValidationError validationError = createValidationError(request, status, "Validation exception", ex);
        return ResponseEntity.status(status).body(validationError);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFoundException(ResourceNotFoundException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError standardError = createStandardError(request, status, "Resource not found", ex);
        return ResponseEntity.status(status).body(standardError);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<StandardError> unauthorizedException(UnauthorizedException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        StandardError standardError = createStandardError(request, status, "Unauthorized", ex);
        return ResponseEntity.status(status).body(standardError);
    }

    private ValidationError createValidationError(HttpServletRequest request, HttpStatus status, String error, MethodArgumentNotValidException ex) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        ValidationError validationError =
                ValidationError.builder()
                .timestamp(Instant.now())
                .status(status.value())
                .error(error)
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .errors(new ArrayList<>())
                .build();
        fieldErrors.forEach(f -> validationError.addError(f.getField(), f.getDefaultMessage()));
        return validationError;
    }

    private StandardError createStandardError(HttpServletRequest request, HttpStatus status, String error, Exception ex) {
        return StandardError.builder()
                        .timestamp(Instant.now())
                        .status(status.value())
                        .error(error)
                        .message(ex.getMessage())
                        .path(request.getRequestURI())
                        .build();
    }
}
