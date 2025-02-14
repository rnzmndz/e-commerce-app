package net.renzo.mapper;

import static org.junit.jupiter.api.Assertions.*;

import net.renzo.dto.ProductListDTO;
import net.renzo.model.Brand;
import net.renzo.model.Category;
import net.renzo.model.Product;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class ProductListMapperTest {

    private final ProductListMapper mapper = Mappers.getMapper(ProductListMapper.class);

    @Test
    void testToProductListDTO() {
        Product product = new Product();
        product.setCategory(Category.builder().id(1L).name("Electronics").description("Electronic devices").build());
        product.setBrand(Brand.builder().id(1L).name("Apple").build());
        // Set other properties of product

        ProductListDTO dto = mapper.toProductListDTO(product);

        assertEquals("Electronics", dto.getCategoryName());
        assertEquals("BrandName", dto.getBrandName());
        // Add other assertions
    }

    @Test
    void testToProduct() {
        ProductListDTO dto = new ProductListDTO();
        dto.setCategoryName("Electronics");
        dto.setBrandName("BrandName");
        // Set other properties of dto

        Product product = mapper.toProduct(dto);

        assertEquals("Electronics", product.getCategory().getName());
        assertEquals("BrandName", product.getBrand().getName());
        // Add other assertions
    }
}