package net.renzo.userservice.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@Builder
public class ErrorResponse extends RuntimeException {
    private int status;
    private String message;
    private long timeStamp;
}
