package com.laerson.techsolutio.techproduct.domain.service;

import com.laerson.techsolutio.techproduct.api.dto.request.ProductRequestBody;
import com.laerson.techsolutio.techproduct.api.dto.response.ProductResponseBody;
import com.laerson.techsolutio.techproduct.core.modelmapper.interfaces.IModelMapperDTOConverter;
import com.laerson.techsolutio.techproduct.domain.entity.Product;
import com.laerson.techsolutio.techproduct.domain.exception.ProductNotFoundException;
import com.laerson.techsolutio.techproduct.domain.repository.ProductRepository;
import com.laerson.techsolutio.techproduct.domain.service.interfaces.IAuthenticationService;
import com.laerson.techsolutio.techproduct.domain.service.interfaces.IProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;


@Service
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final IModelMapperDTOConverter dtoConverter;
    private final IAuthenticationService iAuthenticationService;

    public ProductService(ProductRepository productRepository, IModelMapperDTOConverter dtoConverter, IAuthenticationService iAuthenticationService) {
        this.productRepository = productRepository;
        this.dtoConverter = dtoConverter;
        this.iAuthenticationService = iAuthenticationService;
    }


    @Override
    public ProductResponseBody createProduct(ProductRequestBody productRequestBody, HttpServletRequest request) {
        iAuthenticationService.validadTokenByRequest(request);
        Product newProduct = dtoConverter.convertToEntity(productRequestBody, Product.class);
        newProduct.setRandomId();
        productRepository.save(newProduct);
        ProductResponseBody productCreated = dtoConverter.convertToModelDTO(newProduct, ProductResponseBody.class);
        return productCreated;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProductResponseBody> findAllPaged(PageRequest pageRequest, HttpServletRequest request) {
        iAuthenticationService.validadTokenByRequest(request);
        Page<Product> products = productRepository.findAll(pageRequest);
        Page<ProductResponseBody> responseBodyPage = dtoConverter.convertToModelPageDTO(products, ProductResponseBody.class);
        return responseBodyPage;
    }

    @Override
    public ProductResponseBody findProductById(UUID productId, HttpServletRequest request) {
        iAuthenticationService.validadTokenByRequest(request);
        Product product = findProduct(productId);
        ProductResponseBody productResponseBody = dtoConverter.convertToModelDTO(product, ProductResponseBody.class);
        return productResponseBody;
    }

    @Override
    public ProductResponseBody updateProduct(UUID productId, ProductRequestBody productRequestBody, HttpServletRequest request) {
        iAuthenticationService.validadTokenByRequest(request);
        Product product = findProduct(productId);
        BeanUtils.copyProperties(productRequestBody, product, "id");
        Product productUpdated = productRepository.save(product);
        ProductResponseBody productResponseBody = dtoConverter.convertToModelDTO(productUpdated, ProductResponseBody.class);
        return productResponseBody;
    }

    @Override
    public void deleteProduct(UUID productId, HttpServletRequest request) {
        iAuthenticationService.validadTokenByRequest(request);
        Product product = findProduct(productId);
        productRepository.deleteById(product.getId());
    }

    private Product findProduct(UUID id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException());
    }

}
