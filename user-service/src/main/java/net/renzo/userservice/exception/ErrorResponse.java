package net.renzo.userservice.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@Builder
public class ErrorResponse{
    private int status;
    private String message;
    private long timeStamp;
}
