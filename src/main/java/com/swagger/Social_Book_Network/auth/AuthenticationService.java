package com.swagger.Social_Book_Network.auth;

import com.swagger.Social_Book_Network.role.RoleRepository;
import com.swagger.Social_Book_Network.user.User;

import java.util.List;

public class AuthenticationService {

      private RoleRepository roleRepository;

    public void register(RegistrationRequest request) {

              var userRole = roleRepository.findByName("USER")
                      .orElseThrow(() -> new IllegalStateException("ROLE USer was not initialised"));
              var user = User.builder()
                      .firstName(request.getFirstName())
                      .lastName((request.getLastName()))
                      .email(request.getEmail())
                      .password(request.getPassword())
                      .accountLocked(false)
                      .enabled(false)
                      .roles(List.of(userRole))
                      .build();

    }


}
