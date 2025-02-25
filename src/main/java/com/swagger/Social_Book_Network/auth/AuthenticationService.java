package com.swagger.Social_Book_Network.auth;

import com.swagger.Social_Book_Network.email.EmailService;
import com.swagger.Social_Book_Network.email.EmailTemplateName;
import com.swagger.Social_Book_Network.role.RoleRepository;
import com.swagger.Social_Book_Network.security.JwtService;
import com.swagger.Social_Book_Network.user.Token;
import com.swagger.Social_Book_Network.user.TokenRepository;
import com.swagger.Social_Book_Network.user.User;
import com.swagger.Social_Book_Network.user.UserRepository;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    @Value("${application.mailing.frontend.activation-url}")
    private String activationUrl;
      private final JwtService jwtService;
      private final RoleRepository roleRepository;
      private final UserRepository userRepository;
      private final PasswordEncoder passwordEncoder ;
      private final TokenRepository tokenRepository;
      private final EmailService emailService;
      private final AuthenticationManager authenticationManager;
    public void register(RegistrationRequest request) throws MessagingException {

              var userRole = roleRepository.findByName("USER")
                      .orElseThrow(() -> new IllegalStateException("ROLE User was not initialised"));
              var user = User.builder()
                      .firstName(request.getFirstName())
                      .lastName((request.getLastName()))
                      .email(request.getEmail())
                      .password(passwordEncoder.encode(request.getPassword()))
                      .accountLocked(false)
                      .enabled(false)
                      .roles(List.of(userRole))
                      .build();

              userRepository.save(user);
              sendValidationEmail(user);

    }

    public void sendValidationEmail(User user) throws MessagingException {

        var newToken = generateAndSaveActivationToken( user);

        emailService.sendEmail(user.getEmail(), user.fullName(),
                EmailTemplateName.ACTIVATE_ACCOUNT,
                activationUrl,
                (String) newToken,
                "Account activated");


    }

    private Object generateAndSaveActivationToken(User user) {

        String generatedToken = generateActivationCode(6);
        var token = Token.builder()
                .token(generatedToken)
                .createdAt(LocalDateTime.now())
                        .expiresAt(LocalDateTime.now().plusMinutes(15))
                        .user(user)
                        .build();
        tokenRepository.save(token);
        return generatedToken;
    }

    private String generateActivationCode(int length) {
        String characters = "0123456789";
        StringBuilder codeBuilder = new StringBuilder();
        SecureRandom  secureRandom = new SecureRandom();
        for(int i=0;i<length;i++){
            int randomIndex = secureRandom.nextInt(characters.length());
            codeBuilder.append(characters.charAt(randomIndex));

        }
        return codeBuilder.toString();

    }


    public AuthenticationResponse authenticate(@Valid AuthenticationRequest request) {

      var auth = authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(
                      request.getEmail(),request.getPassword()
              )
      );
      var claims = new HashMap<String,Object>();
      var user =((User)auth.getPrincipal());
      claims.put("fullName" , user.fullName());
      var jwtToken = jwtService.generateToken(claims,user);
        return AuthenticationResponse.builder()
                .token(jwtToken).build();
    }

    public void activateAccount(String token) throws MessagingException {


         Token savedToken = tokenRepository.findByToken(token).orElseThrow(() -> new RuntimeException("Invalid token"));
         if(LocalDateTime.now().isAfter(savedToken.getExpiresAt())){
             sendValidationEmail(savedToken.getUser());
             throw new RuntimeException("Activation token has expired. A new token has been sent");
         }
         var user = userRepository.findById(savedToken.getUser().getId())
                 .orElseThrow(() -> new UsernameNotFoundException("user not found"));
         user.setEnabled(true);
         userRepository.save(user);
         savedToken.setValidatedAt(LocalDateTime.now());
         tokenRepository.save(savedToken);
    }
}
