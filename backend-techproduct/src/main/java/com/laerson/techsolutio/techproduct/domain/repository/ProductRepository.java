package com.laerson.techsolutio.techproduct.domain.repository;

import com.laerson.techsolutio.techproduct.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
}