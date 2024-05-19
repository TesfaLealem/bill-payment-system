package com.example.billpaymentsystemdemo.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.billpaymentsystemdemo.dtos.restData.AuthenticationTokenDetails;
import com.example.billpaymentsystemdemo.dtos.restData.UserDetailsImpl;
import com.example.billpaymentsystemdemo.models.Permissions;
import com.example.billpaymentsystemdemo.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtUtils {

  @Value("${jwt.expirationMs}")
  private int jwtExpirationMs;

  private final SignatureAlgorithm signatureAlgorithm;

  public String generateJwtToken(Authentication authentication) {
    UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
    Date expirationDate = new Date(System.currentTimeMillis() + jwtExpirationMs);

    return JWT.create()
        .withSubject(userPrincipal.getUsername())
        .withIssuedAt(new Date())
        .withExpiresAt(expirationDate)
        .withClaim(
            "ROLE",
            userPrincipal.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
        .sign(signatureAlgorithm.get());
  }

  public AuthenticationTokenDetails parseAccessToken(String token) {
    try {
      JWTVerifier verifier = JWT.require(signatureAlgorithm.get()).build();
      DecodedJWT decodedJWT = verifier.verify(token);
      String username = decodedJWT.getSubject();
      List<String> strings = decodedJWT.getClaim("ROLE").asList(String.class);
      String issuer = decodedJWT.getIssuer();
      Date issuedDate = decodedJWT.getIssuedAt();
      Date expirationDate = decodedJWT.getExpiresAt();

      List<Permissions> roles = strings.stream().map(Permissions::new).toList();
      return AuthenticationTokenDetails.builder()
          .username(username)
          .issuer(issuer)
          .roles(roles)
          .issuedDate(issuedDate)
          .expirationDate(expirationDate)
          .build();

    } catch (Exception e) {
      e.printStackTrace();
      throw new ResourceNotFoundException(e.getMessage());
    }
  }
}
