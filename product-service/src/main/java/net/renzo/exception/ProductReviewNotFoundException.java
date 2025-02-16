package net.renzo.exception;

public class ProductReviewNotFoundException extends RuntimeException {

    public ProductReviewNotFoundException(String message) {
        super(message);
    }
}
