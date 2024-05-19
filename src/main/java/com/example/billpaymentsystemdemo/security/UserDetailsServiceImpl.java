package com.example.billpaymentsystemdemo.security;

import com.example.billpaymentsystemdemo.dtos.restData.UserDetailsImpl;
import com.example.billpaymentsystemdemo.models.Roles;
import com.example.billpaymentsystemdemo.models.Users;
import com.example.billpaymentsystemdemo.repositories.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  private final UserRepository userRepository;

  public UserDetailsServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Users user = userRepository.findByUsername(username);
    if (user == null) {
      throw new UsernameNotFoundException("User not found");
    }
    return new UserDetailsImpl(
        null, user.getUsername(), user.getPassword(), getAuthorities(user.getRole()));
  }

  private Collection<? extends GrantedAuthority> getAuthorities(Roles role) {
    return role.getPermissions().stream()
        .map(permission -> new SimpleGrantedAuthority(permission.getDescription()))
        .toList();
  }
}
