package tn.essat.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.essat.entities.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {

}
