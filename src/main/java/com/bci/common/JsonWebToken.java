package com.bci.common;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class JsonWebToken {

  private static final byte[] SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512).getEncoded();

  private static final String CLAIM_KEY_USERNAME = "username";

  private static final String CLAIM_KEY_CREATED = "created";

  public String generateToken(String username) {

    Map<String, Object> claims = new HashMap<>();
    claims.put(CLAIM_KEY_USERNAME, username);
    claims.put(CLAIM_KEY_CREATED, new Date());
    return generateToken(claims);
  }

  private String generateToken(Map<String, Object> claims) {

    return Jwts.builder()
        .setClaims(claims)
        .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
        .signWith(Keys.hmacShaKeyFor(SECRET_KEY), SignatureAlgorithm.HS512)
        .compact();
  }

}
