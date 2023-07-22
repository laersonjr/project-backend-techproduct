package com.laerson.techsolutio.techproduct.domain.service.interfaces;

import com.laerson.techsolutio.techproduct.api.dto.request.ProductRequestBody;
import com.laerson.techsolutio.techproduct.api.dto.response.ProductResponseBody;

public interface IProductService {
    ProductResponseBody createProduct(ProductRequestBody productRequestBody);
}
