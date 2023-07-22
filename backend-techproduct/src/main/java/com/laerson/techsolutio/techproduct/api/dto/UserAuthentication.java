package com.laerson.techsolutio.techproduct.api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAuthentication {

    @NotBlank
    private String name;

    @NotBlank
    private String password;

}
