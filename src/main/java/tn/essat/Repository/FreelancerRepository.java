package tn.essat.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.essat.entities.Freelancer;
import tn.essat.entities.Role;
import tn.essat.entities.User;

import java.util.List;

public interface FreelancerRepository extends JpaRepository<Freelancer, Long> {




    @Query("SELECT u FROM User u WHERE u.role = 'FREELANCER'")
    List<User> findFreelancers();

}

