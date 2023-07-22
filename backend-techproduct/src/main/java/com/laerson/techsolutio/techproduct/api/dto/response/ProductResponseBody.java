package com.laerson.techsolutio.techproduct.api.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class ProductResponseBody {

    private UUID id;
    private String name;
    private String supplier;
    private BigDecimal value;

}
