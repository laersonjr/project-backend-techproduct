package com.laerson.techsolutio.techproduct.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TokenResponseBody {

    private String token;
    private int expiresIn;
    private OffsetDateTime expirationDate;

}
