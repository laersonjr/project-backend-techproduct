package com.laerson.techsolutio.techproduct.core.security.interfaces;

import java.util.Date;

public interface ITokenProvide {

    String generateToken(String name);
    boolean validateToken(String token);
    String getNameFromToken(String token);
    Date getExpiryDateFromToken(String token);
    int getJwtExpirationMs();

}
