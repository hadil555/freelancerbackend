package tn.essat.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.essat.entities.Project;

public interface ProjectRepository extends JpaRepository<Project,Long >{

}
