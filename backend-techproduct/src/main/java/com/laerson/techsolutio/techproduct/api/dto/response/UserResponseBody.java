package com.laerson.techsolutio.techproduct.api.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserResponseBody {

    private UUID id;
    private String name;

}
