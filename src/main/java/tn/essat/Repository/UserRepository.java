package tn.essat.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import jakarta.transaction.Transactional;
import tn.essat.entities.Role;
import tn.essat.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String email);
	 Boolean   existsByEmail(String email);

	List<User> findByRole(Role role);
	@Modifying
	   @Transactional
	   @Query("update User u set u.password = ?2 where u.email = ?1 ")
	   void updatePassword(String email, String password);


}
