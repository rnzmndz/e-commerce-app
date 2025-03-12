package net.renzo.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;

class ProductTest {

    private Product product;
    private Brand brand;
    private Category category;

    @BeforeEach
    void setUp() {
        brand = Brand.builder().id(1L).name("Apple").products(new HashSet<>()).build();
        category = Category.builder().id(1L).name("Electronics").products(new HashSet<>()).build();
        product = Product.builder()
                .id(1L)
                .name("Smartphone")
                .description("A high-end smartphone with 128GB storage.")
                .sku("SKU12345")
                .brand(brand)
                .category(category)
                .build();
    }

    @Test
    void testAddImage() {
        Image image = new Image();
        image.setId(1L);
        image.setUrl("http://example.com/image.jpg");
        image.setIsPrimary(true);

        product.addImage(image);

        assertTrue(product.getImages().contains(image));
        assertEquals(product, image.getProduct());
    }

    @Test
    void testRemoveImage() {
        Image image = new Image();
        image.setId(1L);
        image.setUrl("http://example.com/image.jpg");
        image.setIsPrimary(true);

        product.addImage(image);
        product.removeImage(image);

        assertFalse(product.getImages().contains(image));
        assertNull(image.getProduct());
    }

    @Test
    void testAddVariant() {
        Variant variant = new Variant();
        variant.setId(1L);
        variant.setName("128GB");

        product.addVariant(variant);

        assertTrue(product.getVariants().contains(variant));
        assertEquals(product, variant.getProduct());
    }

    @Test
    void testRemoveVariant() {
        Variant variant = new Variant();
        variant.setId(1L);
        variant.setName("128GB");

        product.addVariant(variant);
        product.removeVariant(variant);

        assertFalse(product.getVariants().contains(variant));
        assertNull(variant.getProduct());
    }

    @Test
    void testAddAttribute() {
        Attribute attribute = new Attribute();
        attribute.setId(1L);
        attribute.setKey("Color");

        product.addAttribute(attribute);

        assertTrue(product.getAttributes().contains(attribute));
        assertTrue(attribute.getProducts().contains(product));
    }

    @Test
    void testRemoveAttribute() {
        Attribute attribute = new Attribute();
        attribute.setId(1L);
        attribute.setKey("Color");

        product.addAttribute(attribute);
        product.removeAttribute(attribute);

        assertFalse(product.getAttributes().contains(attribute));
        assertFalse(attribute.getProducts().contains(product));
    }

    @Test
    void testAddReview() {
        Review review = new Review();
        review.setId(1L);
        review.setComment("Great product!");

        product.addReview(review);

        assertTrue(product.getReviews().contains(review));
        assertEquals(product, review.getProduct());
    }

    @Test
    void testRemoveReview() {
        Review review = new Review();
        review.setId(1L);
        review.setComment("Great product!");

        product.addReview(review);
        product.removeReview(review);

        assertFalse(product.getReviews().contains(review));
        assertNull(review.getProduct());
    }

    @Test
    void testSetBrand() {
        Brand newBrand = Brand.builder().id(2L).name("Samsung").products(new HashSet<>()).build();

        product.setBrand(newBrand);

        assertEquals(newBrand, product.getBrand());
        assertTrue(newBrand.getProducts().contains(product));
        assertFalse(brand.getProducts().contains(product));
    }

    @Test
    void testGetAttributes() {
        Attribute attribute1 = new Attribute();
        attribute1.setId(1L);
        attribute1.setKey("Color");

        Attribute attribute2 = new Attribute();
        attribute2.setId(2L);
        attribute2.setKey("Size");

        product.addAttribute(attribute1);
        product.addAttribute(attribute2);

        assertEquals(2, product.getAttributes().size());
        assertTrue(product.getAttributes().contains(attribute1));
        assertTrue(product.getAttributes().contains(attribute2));
    }
}