package tn.essat.service;

import java.util.List;

import tn.essat.Dto.ProjectDto;
import tn.essat.exception.MessageNotFoundException;
import tn.essat.exception.ProjectNotFoundException;
import org.springframework.security.core.Authentication;


public interface ProjectService {

    ProjectDto addProjet(ProjectDto projectDto , Authentication  connectedUser)throws  ProjectNotFoundException;

    ProjectDto getProjecctById(Long id) throws  ProjectNotFoundException;
    

    List<ProjectDto> getAllProjets();

    void deleteProjectById(Long id) throws  ProjectNotFoundException;

    ProjectDto updateProjet(Long id, ProjectDto projectDto)throws  ProjectNotFoundException;

}
