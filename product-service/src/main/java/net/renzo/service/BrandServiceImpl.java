package net.renzo.service;

import net.renzo.dto.BrandDTO;
import net.renzo.exception.BrandNotFoundException;
import net.renzo.exception.ProductNotFoundException;
import net.renzo.mapper.BrandMapper;
import net.renzo.model.Brand;
import net.renzo.model.Product;
import net.renzo.repository.BrandRepository;
import net.renzo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService{

    private final BrandRepository brandRepository;
    private final ProductRepository productRepository;
    private final BrandMapper brandMapper;

    @Autowired
    public BrandServiceImpl(BrandRepository brandRepository, BrandMapper brandMapper,
                            ProductRepository productRepository) {
        this.brandRepository = brandRepository;
        this.brandMapper = brandMapper;
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public BrandDTO createBrand(BrandDTO brand) {
        // Convert the BrandDTO to a Brand entity
        Brand brandEntity = brandMapper.toEntity(brand);

        // Save the brand entity to the database
        brandEntity = brandRepository.save(brandEntity);

        // Convert the saved entity back to a DTO
        return brandMapper.toDTO(brandEntity);
    }

    @Override
    public Optional<BrandDTO> findBrandById(Long id) {
        // Find the brand entity by its ID
        Brand brand = brandRepository.findById(id).orElseThrow(
                () -> new BrandNotFoundException("Brand not found")
        );

        return Optional.of(brandMapper.toDTO(brand));
    }

    @Override
    public Optional<BrandDTO> findBrandByName(String name) {
        // Find the brand entity by its name
        Brand brand = brandRepository.findByName(name).orElseThrow(
                () -> new BrandNotFoundException("Brand not found")
        );

        return Optional.of(brandMapper.toDTO(brand));
    }
    @Override
    public Page<BrandDTO> findAllBrands(Pageable pageable) {
        Page<Brand> brandPage = brandRepository.findAll(pageable);
        return brandPage.map(brandMapper::toDTO);
    }

    @Override
    @Transactional
    public BrandDTO updateBrand(Long id, BrandDTO brandDTO) {
        // Find the existing brand entity by its ID
        Brand existingBrand = brandRepository.findById(id).orElseThrow(
                () -> new BrandNotFoundException("Brand not found")
        );

        // Update the existing brand entity with the new data
        brandMapper.updateEntity(brandDTO, existingBrand);

        // Save the updated brand entity to the database
        Brand updatedBrand = brandRepository.save(existingBrand);

        // Convert the updated entity back to a DTO
        return brandMapper.toDTO(updatedBrand);
    }

    @Override
    @Transactional
    public void addProductToBrand(Long brandId, Long productId) {
        Brand brand = brandRepository.findById(brandId)
                .orElseThrow(() -> new BrandNotFoundException("Brand not found")
        );
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found")
        );
        brand.addProduct(product);
        brandRepository.save(brand);
    }

    @Override
    @Transactional
    public void removeProductFromBrand(Long brandId, Long productId) {
        Brand brand = brandRepository.findById(brandId)
                .orElseThrow(() -> new BrandNotFoundException("Brand not found"));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
        brand.removeProduct(product);
        brandRepository.save(brand);
    }

    @Override
    public void deleteBrand(Long id) {
        // Find the existing brand entity by its ID
        Brand existingBrand = brandRepository.findById(id).orElseThrow(
                () -> new BrandNotFoundException("Brand not found")
        );

        // Delete the brand entity from the database
        brandRepository.delete(existingBrand);
    }
}
