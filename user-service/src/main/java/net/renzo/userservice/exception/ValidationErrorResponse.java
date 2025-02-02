package net.renzo.userservice.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@Builder
public class ValidationErrorResponse {

    private LocalDateTime timestamp;
    private int status;
    private String message;

    @Builder.Default
    private Map<String, String> errors = new HashMap<>();

    public void addError(String field, String message) {
        errors.put(field, message);
    }
}
