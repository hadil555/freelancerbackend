package tn.essat.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.essat.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
