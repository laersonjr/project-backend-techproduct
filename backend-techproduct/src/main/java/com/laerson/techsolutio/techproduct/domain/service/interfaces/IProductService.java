package com.laerson.techsolutio.techproduct.domain.service.interfaces;

import com.laerson.techsolutio.techproduct.api.dto.request.ProductRequestBody;
import com.laerson.techsolutio.techproduct.api.dto.response.ProductResponseBody;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

public interface IProductService {
    ProductResponseBody createProduct(ProductRequestBody productRequestBody);

    Page<ProductResponseBody> findAllPaged(PageRequest pageRequest);

    ProductResponseBody findProductById(UUID productId);

    ProductResponseBody updateProduct(UUID productId, ProductRequestBody productRequestBody);

    void deleteProduct(UUID productId);
}
