package net.renzo.mapper;

import static org.junit.jupiter.api.Assertions.*;

import net.renzo.dto.ProductDetailDTO;
import net.renzo.model.Brand;
import net.renzo.model.Category;
import net.renzo.model.Product;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class ProductMapperTest {

    private final ProductMapper mapper = Mappers.getMapper(ProductMapper.class);

    @Test
    void testToDto() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Smartphone");
        product.setDescription("A high-end smartphone with 128GB storage.");
        product.setSku("SKU12345");
        product.setCategory(Category.builder().id(1L).name("Electronics").description("Electronic devices").build());
        product.setBrand(Brand.builder().id(1L).name("Apple").build());

        ProductDetailDTO dto = mapper.toDto(product);

        assertNotNull(dto);
        assertEquals(product.getId(), dto.getId());
        assertEquals(product.getName(), dto.getName());
        assertEquals(product.getDescription(), dto.getDescription());
        assertEquals(product.getSku(), dto.getSku());
        assertEquals(product.getCategory().getName(), dto.getCategoryName());
        assertEquals(product.getBrand().getName(), dto.getBrandName());
    }

    @Test
    void testToEntity() {
        ProductDetailDTO dto = new ProductDetailDTO();
        dto.setId(1L);
        dto.setName("Smartphone");
        dto.setDescription("A high-end smartphone with 128GB storage.");
        dto.setSku("SKU12345");
        dto.setCategoryName("Electronics");
        dto.setBrandName("Apple");

        Product product = mapper.toEntity(dto);

        assertNotNull(product);
        assertEquals(dto.getId(), product.getId());
        assertEquals(dto.getName(), product.getName());
        assertEquals(dto.getDescription(), product.getDescription());
        assertEquals(dto.getSku(), product.getSku());
        assertEquals(dto.getCategoryName(), product.getCategory().getName());
        assertEquals(dto.getBrandName(), product.getBrand().getName());
    }

    @Test
    void testUpdateEntity() {
        ProductDetailDTO dto = new ProductDetailDTO();
        dto.setId(1L);
        dto.setName("Smartphone");
        dto.setDescription("A high-end smartphone with 128GB storage.");
        dto.setSku("SKU12345");
        dto.setCategoryName("Electronics");
        dto.setBrandName("Apple");

        Product product = new Product();
        product.setId(2L);
        product.setName("Old Phone");
        product.setDescription("An old phone with 64GB storage.");
        product.setSku("SKU54321");
        product.setCategory(Category.builder().id(2L).name("Old Electronics").description("Old electronic devices").build());
        product.setBrand(Brand.builder().id(2L).name("Samsung").build());

        mapper.updateEntity(dto, product);

        assertEquals(dto.getId(), product.getId());
        assertEquals(dto.getName(), product.getName());
        assertEquals(dto.getDescription(), product.getDescription());
        assertEquals(dto.getSku(), product.getSku());
        assertEquals(dto.getCategoryName(), product.getCategory().getName());
        assertEquals(dto.getBrandName(), product.getBrand().getName());
    }
}