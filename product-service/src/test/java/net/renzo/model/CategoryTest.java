package net.renzo.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class CategoryTest {

    @Test
    void addProduct() {
        Category category = new Category();
        Product product = new Product();
        category.addProduct(product);
        assertTrue(category.getProducts().contains(product));
    }

    @Test
    void removeProduct() {
        Category category = new Category();
        Product product = new Product();
        category.addProduct(product);
        category.removeProduct(product);
        assertFalse(category.getProducts().contains(product));
    }
}