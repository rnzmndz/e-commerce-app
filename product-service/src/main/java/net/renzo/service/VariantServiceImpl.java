package net.renzo.service;

import net.renzo.dto.VariantDTO;
import net.renzo.mapper.VariantMapper;
import net.renzo.repository.VariantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
// TODO Create Implementation of VariantService
public class VariantServiceImpl implements VariantService{

    private final VariantRepository variantRepository;
    private final VariantMapper variantMapper;

    @Autowired
    public VariantServiceImpl(VariantRepository variantRepository, VariantMapper variantMapper) {
        this.variantRepository = variantRepository;
        this.variantMapper = variantMapper;
    }
    @Override
    public VariantDTO createProductVariant(VariantDTO variantDTO) {
        return null;
    }

    @Override
    public VariantDTO getProductVariantById(Long id) {
        return null;
    }

    @Override
    public Page<VariantDTO> getProductVariantsByProductId(Long productId, Pageable pageable) {
        return null;
    }

    @Override
    public Page<VariantDTO> getAllProductVariants(Pageable pageable) {
        return null;
    }

    @Override
    public VariantDTO updateProductVariant(Long id, VariantDTO variantDTO) {
        return null;
    }

    @Override
    public void deleteProductVariant(Long id) {

    }
}
