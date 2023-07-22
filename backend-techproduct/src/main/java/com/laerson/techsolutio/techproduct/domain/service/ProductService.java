package com.laerson.techsolutio.techproduct.domain.service;

import com.laerson.techsolutio.techproduct.api.dto.request.ProductRequestBody;
import com.laerson.techsolutio.techproduct.api.dto.response.ProductResponseBody;
import com.laerson.techsolutio.techproduct.core.modelmapper.interfaces.IModelMapperDTOConverter;
import com.laerson.techsolutio.techproduct.domain.entity.Product;
import com.laerson.techsolutio.techproduct.domain.repository.ProductRepository;
import com.laerson.techsolutio.techproduct.domain.service.interfaces.IProductService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final IModelMapperDTOConverter dtoConverter;

    public ProductService(ProductRepository productRepository, IModelMapperDTOConverter dtoConverter) {
        this.productRepository = productRepository;
        this.dtoConverter = dtoConverter;
    }

    @Override
    public ProductResponseBody createProduct(ProductRequestBody productRequestBody) {
        Product newProduct = dtoConverter.convertToEntity(productRequestBody, Product.class);
        newProduct.setId(UUID.randomUUID());
        productRepository.save(newProduct);
        ProductResponseBody productCreated = dtoConverter.convertToModelDTO(newProduct, ProductResponseBody.class);
        return productCreated;
    }
}
