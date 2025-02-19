package net.renzo.mapper;

        import static org.junit.jupiter.api.Assertions.*;

        import net.renzo.dto.ProductDTO;
        import net.renzo.model.Brand;
        import net.renzo.model.Product;
        import org.junit.jupiter.api.Test;
        import org.mapstruct.factory.Mappers;

        import java.util.HashSet;

class ProductMapperTest {

            private ProductMapper mapper = Mappers.getMapper(ProductMapper.class);

            @Test
            void testToDto() {
                Product product = new Product();
                product.setId(1L);
                product.setName("Smartphone");
                product.setDescription("A high-end smartphone with 128GB storage.");
                product.setSku("SKU12345");
                product.setBrand(Brand.builder().id(1L).name("Apple").products(new HashSet<>()).build());

                ProductDTO dto = mapper.toDto(product);

                assertNotNull(dto);
                assertEquals(product.getId(), dto.getId());
                assertEquals(product.getName(), dto.getName());
                assertEquals(product.getDescription(), dto.getDescription());
                assertEquals(product.getSku(), dto.getSku());
                assertEquals(product.getBrand().getName(), dto.getBrandName());
            }

            @Test
            void testToEntity() {
                ProductDTO dto = new ProductDTO();
                dto.setId(1L);
                dto.setName("Smartphone");
                dto.setDescription("A high-end smartphone with 128GB storage.");
                dto.setSku("SKU12345");
                dto.setBrandName("Apple");

                Product product = mapper.toEntity(dto);

                assertNotNull(product);
                assertEquals(dto.getId(), product.getId());
                assertEquals(dto.getName(), product.getName());
                assertEquals(dto.getDescription(), product.getDescription());
                assertEquals(dto.getSku(), product.getSku());
                assertEquals(dto.getBrandName(), product.getBrand().getName());
            }
        }