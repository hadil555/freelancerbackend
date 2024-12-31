package tn.essat.auth;



import static tn.essat.auth.UserService.applicationUrl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import tn.essat.Config.JwtService;
import tn.essat.Dto.*;
import tn.essat.Repository.ClientRepository;
import tn.essat.Repository.FreelancerRepository;
import tn.essat.Repository.UserRepository;
import tn.essat.entities.*;
import tn.essat.listner.RegistrationCompleteEvent;



@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final UserRepository repository;
  private  final ClientRepository clientRepository;
  private final FreelancerRepository freelancerRepository;
  private final TokenRepository tokenRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;
  private final ApplicationEventPublisher publisher;
//methode register
  public ResponseEntity<Response>  register(RegisterRequest userRequest,final HttpServletRequest request) {
   // System.err.println(userRequest.getRole());
  
    boolean userExists = repository.findAll()
            .stream()
            .anyMatch(user -> userRequest.getEmail().equalsIgnoreCase(user.getEmail()));

    if (userExists) {
      return ResponseEntity.badRequest().body(Response.builder()
              .responseMessage("User with provided email  already exists!")
              .build());
    }

    if (userRequest instanceof FreelancerDto) {
    	Freelancer user = new Freelancer();
    	   user = FreelancerDto.toEntity((FreelancerDto) userRequest);
    	   user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
    	   user.setRole(Role.FREELANCER);
    	   var savedUser = freelancerRepository.save(user);
    	  // publisher.publishEvent(new RegistrationCompleteEvent(savedUser, applicationUrl(request)));

    	   return new ResponseEntity<>(
    	           Response.builder()

    	                   .responseMessage("Success! Please, check your email to complete your registration")
    	                   .email(savedUser.getEmail())
    	                   .build(),
    	           HttpStatus.CREATED
    	   );
    	}
      if (userRequest instanceof ClientDto) {
          Client user = new Client();
          user = ClientDto.toEntity((ClientDto) userRequest);
          user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
          user.setRole(Role.CLIENT);
          var savedUser =clientRepository .save(user);
         // publisher.publishEvent(new RegistrationCompleteEvent(savedUser, applicationUrl(request)));

          return new ResponseEntity<>(
                  Response.builder()

                          .responseMessage("Success! Please, check your email to complete your registration")
                          .email(savedUser.getEmail())
                          .build(),
                  HttpStatus.CREATED
          );
      }

    return null;
  }
//seed
  
  @PostConstruct
  public void createDefaultAdmin() {
      User userADM =new User();
      
      String email50 = "adm@mail.com";
      if (!repository.existsByEmail(email50)) {
    	  userADM.setEmail("adm@mail.com");
    	  userADM.setUsername("hammadi");
    	  userADM.setPassword(passwordEncoder.encode("adm"));
    	  userADM.setRole(Role.FREELANCER);
          repository.save(userADM);
      }
//
     
      
      
   
      
    
      
      
  }
  //
  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getEmail(),
            request.getPassword()
        )
    );
    var user = repository.findByEmail(request.getEmail())
        .orElseThrow();
    
   var claims = new HashMap<String, Object>();
   claims.put("usernamename", user.getUsername());
   //claims.put("role", user.getRole().name());
   claims.put("userId", user.getId());
    var jwtToken = jwtService.generateToken(claims,user);
    
    var refreshToken = jwtService.generateRefreshToken(user);
    revokeAllUserTokens(user);
    saveUserToken(user, jwtToken);
    return AuthenticationResponse.builder()
        .accessToken(jwtToken)
            .refreshToken(refreshToken)
        .build();
  }

  private void saveUserToken(User user, String jwtToken) {
    var token = Token.builder()
        .user(user)
        .token(jwtToken)
        .tokenType(TokenType.BEARER)
        .expired(false)
        .revoked(false)
        .build();
    tokenRepository.save(token);
  }

  private void revokeAllUserTokens(User user) {
    var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
    if (validUserTokens.isEmpty())
      return;
    validUserTokens.forEach(token -> {
      token.setExpired(true);
      token.setRevoked(true);
    });
    tokenRepository.saveAll(validUserTokens);
  }

  public void refreshToken(
          HttpServletRequest request,
          HttpServletResponse response
  ) throws IOException {
    final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
    final String refreshToken;
    final String userEmail;
    if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
      return;
    }
    refreshToken = authHeader.substring(7);
    userEmail = jwtService.extractUsername(refreshToken);
    if (userEmail != null) {
      var user = this.repository.findByEmail(userEmail)
              .orElseThrow();
      if (jwtService.isTokenValid(refreshToken, user)) {
        var accessToken = jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, accessToken);
        var authResponse = AuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
        new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
      }
    }
  }
}

