package net.renzo.userservice.exception;

public class AuthorityNotFoundException extends RuntimeException{
    public AuthorityNotFoundException(String message) {
        super(message);
    }

    public AuthorityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthorityNotFoundException(Throwable cause) {
        super(cause);
    }
}
