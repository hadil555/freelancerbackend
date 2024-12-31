package tn.essat.auth;

import org.springframework.http.ResponseEntity;

import tn.essat.Dto.Response;
import tn.essat.entities.User;
import tn.essat.entities.VerificationToken;






public interface VerificationTokenService {
	
   void saveUserVerificationToken(User user, String token);
   String validateToken(String token);
   ResponseEntity<Response> verifyEmail(String token);
   VerificationToken generateNewVerificationToken(String oldToken);
}
