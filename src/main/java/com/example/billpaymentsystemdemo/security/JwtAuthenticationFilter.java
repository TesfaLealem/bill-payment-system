package com.example.billpaymentsystemdemo.security;


import com.example.billpaymentsystemdemo.dtos.restData.AuthenticationTokenDetails;
import com.example.billpaymentsystemdemo.models.Permissions;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.*;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private JwtUtils jwtUtils;

  public JwtAuthenticationFilter(JwtUtils jwtUtils) {
    this.jwtUtils = jwtUtils;
  }

  @Override
  protected void doFilterInternal(
      @NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response,
      @NonNull FilterChain filterChain)
      throws ServletException, IOException {
    if (request.getServletPath().equals("/auth/login")
        || request.getServletPath().equals("auth/refresh_token")) {
      filterChain.doFilter(request, response);
    } else {
      String authorizationHeader = request.getHeader(AUTHORIZATION);
      if (authorizationHeader != null) {
        try {

          AuthenticationTokenDetails tokenDetails = jwtUtils.parseAccessToken(authorizationHeader);
          List<Permissions> roles = tokenDetails.getRoles();
          Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

          roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getDescription())));
          UsernamePasswordAuthenticationToken authenticationToken =
              new UsernamePasswordAuthenticationToken(
                  tokenDetails.getUsername(), null, authorities);
          SecurityContextHolder.getContext().setAuthentication(authenticationToken);

          // Passing the request to the next filter in the chain
          filterChain.doFilter(request, response);
        } catch (Exception e) {
          response.setContentType(MediaType.APPLICATION_JSON_VALUE);
          response.setHeader("error", e.getMessage());
          response.setStatus(FORBIDDEN.value());
          Map<String, String> error = new HashMap<>();
          error.put("error_message", e.getMessage());
        }
      } else {
        filterChain.doFilter(request, response);
      }
    }
  }
}
