package com.laerson.techsolutio.techproduct.core.security;

import com.laerson.techsolutio.techproduct.core.security.exception.TokenInvalidException;
import com.laerson.techsolutio.techproduct.core.security.interfaces.ITokenProvide;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

@Component
public class TokenProvide implements ITokenProvide {

    private SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    private String jwtSecret = Encoders.BASE64.encode(key.getEncoded());
    private final int jwtExpirationMs = 7200000;

    @Override
    public String generateToken(String name) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationMs);
        return Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .setSubject(name)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    @Override
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            throw new TokenInvalidException();
        }
    }

    @Override
    public String getNameFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    @Override
    public OffsetDateTime getExpiryDateFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        Date expirationDate = claims.getExpiration();
        Instant instant = expirationDate.toInstant();
        return instant.atZone(ZoneId.systemDefault()).toOffsetDateTime();
    }

    public int getJwtExpirationMs() {
        return jwtExpirationMs;
    }
}
