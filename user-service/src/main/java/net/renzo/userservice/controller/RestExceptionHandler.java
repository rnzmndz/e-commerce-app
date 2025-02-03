package net.renzo.userservice.controller;

import net.renzo.userservice.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

/**
 * Global exception handler for REST controllers.
 * <p>
 * This class handles exceptions thrown by REST controllers and provides appropriate
 * HTTP responses with error details.
 */
@ControllerAdvice
public class RestExceptionHandler {

    /**
     * Handles UserAlreadyExistsException.
     *
     * @param exception the exception thrown when a user already exists
     * @return a ResponseEntity containing the error response with status 409 (Conflict)
     */
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleUserAlreadyExistsException(UserAlreadyExistsException exception) {
        // Build the error response with status 409, the exception message, and the current timestamp
        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(409)
                .message(exception.getMessage())
                .timeStamp(System.currentTimeMillis())
                .build();

        // Return the error response wrapped in a ResponseEntity with HTTP status 409 (Conflict)
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    /**
     * Handles UserNotFoundException.
     *
     * @param exception the exception thrown when a user is not found
     * @return a ResponseEntity containing the error response with status 404 (Not Found)
     */
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException exception) {
        // Build the error response with status 404, the exception message, and the current timestamp
        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(404)
                .message(exception.getMessage())
                .timeStamp(System.currentTimeMillis())
                .build();

        // Return the error response wrapped in a ResponseEntity with HTTP status 404 (Not Found)
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles validation exceptions for method arguments.
     *
     * @param ex the MethodArgumentNotValidException thrown when validation fails
     * @return a ResponseEntity containing the validation error response with status 400 (Bad Request)
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleValidationExceptions(
            MethodArgumentNotValidException ex) {

        // Build the validation error response with status 400, a generic message, and the current timestamp
        ValidationErrorResponse errors = ValidationErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message("Validation failed")
                .timestamp(LocalDateTime.now())
                .build();

        // Iterate over the field errors and add each error to the validation error response
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.addError(error.getField(), error.getDefaultMessage())
        );

        // Return the validation error response wrapped in a ResponseEntity with HTTP status 400 (Bad Request)
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleForbiddenException(ForbiddenException exception) {
        // Build the error response with status 403, the exception message, and the current timestamp
        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(403)
                .message(exception.getMessage())
                .timeStamp(System.currentTimeMillis())
                .build();

        // Return the error response wrapped in a ResponseEntity with HTTP status 403 (Forbidden)
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }
}
