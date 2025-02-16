package net.renzo.exception;

public class ProductAttributeNotFoundException extends RuntimeException {
    public ProductAttributeNotFoundException(String message) {
        super(message);
    }
}
