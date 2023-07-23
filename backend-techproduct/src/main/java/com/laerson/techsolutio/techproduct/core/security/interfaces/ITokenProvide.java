package com.laerson.techsolutio.techproduct.core.security.interfaces;

import java.time.OffsetDateTime;
import java.util.Date;

public interface ITokenProvide {

    String generateToken(String name);
    boolean validateToken(String token);
    String getNameFromToken(String token);
    OffsetDateTime getExpiryDateFromToken(String token);
    int getJwtExpirationMs();

}
