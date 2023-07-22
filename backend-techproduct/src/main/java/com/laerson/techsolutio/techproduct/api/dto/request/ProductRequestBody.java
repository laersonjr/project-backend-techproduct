package com.laerson.techsolutio.techproduct.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class ProductRequestBody {

    private UUID id;

    @NotBlank
    private String name;

    @NotBlank
    private String supplier;

    @NotNull
    private BigDecimal value;

}
