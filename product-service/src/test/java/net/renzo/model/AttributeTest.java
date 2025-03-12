package net.renzo.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AttributeTest {

    @Test
    void addProduct() {
        Attribute attribute = new Attribute();
        Product product = new Product();
        attribute.addProduct(product);
        assertTrue(attribute.getProducts().contains(product));
        assertTrue(product.getAttributes().contains(attribute));
    }

    @Test
    void removeProduct() {
        Attribute attribute = new Attribute();
        Product product = new Product();
        attribute.addProduct(product);
        attribute.removeProduct(product);
        assertFalse(attribute.getProducts().contains(product));
        assertFalse(product.getAttributes().contains(attribute));
    }

    @Test
    void getProducts() {
        Attribute attribute = new Attribute();
        Product product1 = new Product();
        Product product2 = new Product();
        attribute.addProduct(product1);
        attribute.addProduct(product2);
        assertEquals(2, attribute.getProducts().size());
        assertTrue(attribute.getProducts().contains(product1));
        assertTrue(attribute.getProducts().contains(product2));
    }
}