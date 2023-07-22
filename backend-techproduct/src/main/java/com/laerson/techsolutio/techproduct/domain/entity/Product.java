package com.laerson.techsolutio.techproduct.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity(name = "tb_product")
public class Product {
    @EqualsAndHashCode.Include
    @Id
    private UUID id;
    @NotBlank
    private String name;
    @NotBlank
    private String supplier;
    @NotNull
    private BigDecimal value;

}
