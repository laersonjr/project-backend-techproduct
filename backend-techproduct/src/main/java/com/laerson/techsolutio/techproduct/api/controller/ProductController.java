package com.laerson.techsolutio.techproduct.api.controller;

import com.laerson.techsolutio.techproduct.api.dto.request.ProductRequestBody;
import com.laerson.techsolutio.techproduct.api.dto.response.ProductResponseBody;
import com.laerson.techsolutio.techproduct.domain.service.interfaces.IProductService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final IProductService iProductService;

    public ProductController(IProductService iProductService) {
        this.iProductService = iProductService;
    }

    @PostMapping
    public ResponseEntity<ProductResponseBody> createProduct(@Valid @RequestBody ProductRequestBody productRequestBody) {
        return ResponseEntity.status(HttpStatus.CREATED).body(iProductService.createProduct(productRequestBody));
    }

    @GetMapping
    public ResponseEntity<Page<ProductResponseBody>> findAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy) {

        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        Page<ProductResponseBody> productsPage = iProductService.findAllPaged(pageRequest);

        return ResponseEntity.ok().body(productsPage);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponseBody> findProductById(@PathVariable UUID productId) {
        return ResponseEntity.ok(iProductService.findProductById(productId));
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductResponseBody> updateProduct(@PathVariable UUID productId,
                                                             @Valid @RequestBody ProductRequestBody productRequestBody){
        return ResponseEntity.ok(iProductService.updateProduct(productId, productRequestBody));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID productId){
        iProductService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }



}
