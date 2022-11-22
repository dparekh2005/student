package com.interview.students.controller;

import com.interview.students.model.ERole;
import com.interview.students.domain.Role;
import com.interview.students.domain.User;
import com.interview.students.payload.request.LoginRequest;
import com.interview.students.payload.request.SignupRequest;
import com.interview.students.payload.response.MessageResponse;
import com.interview.students.payload.response.UserInfoResponse;
import com.interview.students.security.jwt.JwtUtils;
import com.interview.students.service.RoleService;
import com.interview.students.service.UserDetailsImpl;
import com.interview.students.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.interview.students.utility.Constants;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private UserDetailsServiceImpl userService;

  @Autowired
  private RoleService roleService;

  @Autowired
  private PasswordEncoder encoder;

  @Autowired
  private JwtUtils jwtUtils;

  @PostMapping("/login")
  public ResponseEntity<Object> login(@RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager
            .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);

    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

    ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

    List<String> roles = userDetails.getAuthorities().stream()
              .map(item -> item.getAuthority())
              .collect(Collectors.toList());

    return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
              .body(new UserInfoResponse(userDetails.getId(),
                      userDetails.getUsername(),
                      userDetails.getEmail(),
                      roles));
  }

  @PostMapping("/logout")
  public ResponseEntity<Object> logout(HttpServletRequest request) {
    String jwt = jwtUtils.getJwtFromCookies(request);
    jwtUtils.blacklistToken(jwt);
    ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
    return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
        .body(new MessageResponse("You've been signed out!"));
  }


  @PostMapping("/signup")
  public ResponseEntity<Object> registerUser(@RequestBody SignupRequest signUpRequest) {
    if (userService.validateUserByUserName(signUpRequest.getUsername())) {
      return ResponseEntity.badRequest().body(new MessageResponse(Constants.usernameAlreadyTaken));
    }

    if (userService.validateUserByEmail(signUpRequest.getEmail())) {
      return ResponseEntity.badRequest().body(new MessageResponse(Constants.emailAlreadyInUse));
    }

    User user = new User(signUpRequest.getUsername(),
            signUpRequest.getEmail(),
            encoder.encode(signUpRequest.getPassword()));

    Set<String> strRoles = signUpRequest.getRole();
    Set<Role> roles = new HashSet<>();

    if (strRoles == null) {
      Role userRole = roleService.findByName(ERole.ROLE_USER)
              .orElseThrow(() -> new RuntimeException(Constants.roleNotFound));
      roles.add(userRole);
    } else {
      strRoles.forEach(role -> {
        switch (role) {
          case "admin":
            Role adminRole = roleService.findByName(ERole.ROLE_ADMIN)
                    .orElseThrow(() -> new RuntimeException(Constants.roleNotFound));
            roles.add(adminRole);
            break;
          case "mod":
            Role modRole = roleService.findByName(ERole.ROLE_MODERATOR)
                    .orElseThrow(() -> new RuntimeException(Constants.roleNotFound));
            roles.add(modRole);
            break;
          default:
            Role userRole = roleService.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException(Constants.roleNotFound));
            roles.add(userRole);
        }
      });
    }
    user.setRoles(roles);
    userService.saveUser(user);

    return ResponseEntity.ok(new MessageResponse(Constants.userRegister));
  }
}
