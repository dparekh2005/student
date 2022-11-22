package com.interview.students.service;

import com.interview.students.domain.User;
import com.interview.students.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service()
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  private UserRepository repository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = repository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
    return UserDetailsImpl.build(user);
  }

  public boolean validateUserByUserName(String username) {
    return repository.existsByUsername(username);
  }

  public boolean validateUserByEmail(String email) {
    return repository.existsByEmail(email);
  }

  public void saveUser(User user) {
    repository.save(user);
  }

}
