package com.laerson.techsolutio.techproduct.domain.service.interfaces;

import com.laerson.techsolutio.techproduct.api.dto.request.ProductRequestBody;
import com.laerson.techsolutio.techproduct.api.dto.response.ProductResponseBody;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

public interface IProductService {
    ProductResponseBody createProduct(ProductRequestBody productRequestBody, HttpServletRequest request);

    Page<ProductResponseBody> findAllPaged(PageRequest pageRequest, HttpServletRequest request);

    ProductResponseBody findProductById(UUID productId, HttpServletRequest request);

    ProductResponseBody updateProduct(UUID productId, ProductRequestBody productRequestBody, HttpServletRequest request);

    void deleteProduct(UUID productId, HttpServletRequest request);
}
