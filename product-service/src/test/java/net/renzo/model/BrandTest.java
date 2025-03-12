package net.renzo.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BrandTest {

    @Test
    void addProduct() {
        Brand brand = new Brand();
        Product product = new Product();
        brand.addProduct(product);

        assertNotNull(brand.getProducts());
        assertTrue(brand.getProducts().contains(product));
        assertEquals(brand, product.getBrand());
    }

    @Test
    void removeProduct() {
        Brand brand = new Brand();
        Product product = new Product();
        brand.addProduct(product);
        brand.removeProduct(product);

        assertFalse(brand.getProducts().contains(product));
        assertNull(product.getBrand());
    }
}