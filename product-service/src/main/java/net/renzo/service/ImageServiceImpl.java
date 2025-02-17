package net.renzo.service;

import net.renzo.dto.ImageDTO;
import net.renzo.exception.ProductImageNotFoundException;
import net.renzo.mapper.ProductImageMapper;
import net.renzo.model.Image;
import net.renzo.repository.ImageRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;
    private final ProductImageMapper productImageMapper;

    public ImageServiceImpl(ImageRepository imageRepository, ProductImageMapper productImageMapper) {
        this.imageRepository = imageRepository;
        this.productImageMapper = productImageMapper;
    }
    @Override
    @Transactional
    public ImageDTO save(ImageDTO imageDTO) {
        // Convert ProductImageDTO to ProductImage entity
        Image image = productImageMapper.toEntity(imageDTO);

        // Save the ProductImage entity to the repository
        image = imageRepository.save(image);

        // Convert the saved ProductImage entity back to ProductImageDTO
        return productImageMapper.toDto(image);
    }

    @Override
    public Optional<ImageDTO> findById(Long id) {
        // Retrieve the ProductImage entity by id, throw exception if not found
        Image image = imageRepository.findById(id)
                .orElseThrow(() -> new ProductImageNotFoundException("Product image not found"));

        // Convert the ProductImage entity to ProductImageDTO and return it wrapped in an Optional
        return Optional.of(productImageMapper.toDto(image));
    }

    @Override
    public Page<ImageDTO> findAll(Pageable pageable) {
        // Retrieve all ProductImage entities from the repository
        Page<Image> productImages = imageRepository.findAll(pageable);

        // Convert the list of ProductImage entities to a list of ProductImageDTOs
        return productImages.map(productImageMapper::toDto);
    }

    @Override
    @Transactional
    public ImageDTO update(ImageDTO imageDTO) {
        // Retrieve the existing ProductImage entity by id, throw exception if not found
        Image existingImage = imageRepository.findById(imageDTO.getId())
                .orElseThrow(() -> new ProductImageNotFoundException("Product image not found"));

        // Update the existing ProductImage entity with values from the DTO
        productImageMapper.updateEntity(imageDTO, existingImage);

        // Save the updated ProductImage entity to the repository
        existingImage = imageRepository.save(existingImage);

        // Convert the updated ProductImage entity back to ProductImageDTO
        return productImageMapper.toDto(existingImage);
    }

    @Override
    public void deleteById(Long id) {
        // Retrieve the existing ProductImage entity by id, throw exception if not found
        Image existingImage = imageRepository.findById(id)
                .orElseThrow(() -> new ProductImageNotFoundException("Product image not found"));

        // Delete the ProductImage entity from the repository
        imageRepository.delete(existingImage);
    }
}
