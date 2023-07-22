package com.laerson.techsolutio.techproduct.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserRequestBody {

    private UUID id;
    
    @NotBlank
    private String name;

    @NotBlank
    private String password;

}
