package com.laerson.techsolutio.techproduct.core.security.interfaces;

public interface ITokenProvide {

    String generateToken(String name);
    boolean validateToken(String token);
    String getNameFromToken(String token);

}
