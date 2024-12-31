package tn.essat.service.Impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import tn.essat.Dto.ProfilDto;
import tn.essat.Dto.ProjectDto;
import tn.essat.Repository.ClientRepository;
import tn.essat.Repository.ProjectRepository;
import tn.essat.Repository.UserRepository;
import tn.essat.entities.Client;
import tn.essat.entities.Profil;
import tn.essat.entities.Project;
import tn.essat.entities.User;
import tn.essat.service.ProjectService;
@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
	private final ProjectRepository projectRepository;
	private final ClientRepository clientRepository;

	@Override
	public ProjectDto addProjet(ProjectDto projectDto , Authentication connectedUser) {
		Project project=ProjectDto.toEntity(projectDto);
		User user = ((User) connectedUser.getPrincipal());

		Optional<Client>clientOptional=clientRepository.findById(user.getId());
		if(clientOptional.isPresent()) {
			project.setClient(clientOptional.get());
			Project projectsaved= projectRepository.save(project);
			return ProjectDto.toDto(projectsaved);
		}else {
			throw new RuntimeException("notfound");
		}
	}

	@Override
	public ProjectDto getProjecctById(Long id) {
		Optional<Project>project = projectRepository.findById(id);
		if(project.isPresent()) {
			return ProjectDto.toDto(project.get());
		}else {
			throw new RuntimeException("notfound");
		}
	}

	@Override
	public List<ProjectDto> getAllProjets() {
		return projectRepository.findAll().stream()
				.map(ProjectDto::toDto)
				.collect(Collectors.toList());
	}

	@Override
	public void deleteProjectById(Long id) {
		getProjecctById(id);
		projectRepository.deleteById(id);
		
	}

	@Override
	public ProjectDto updateProjet(Long id, ProjectDto projectDto) {
		 Project projectExisted = projectRepository.findById(id)
				 .orElseThrow(RuntimeException:: new);
			       
		        projectExisted.setTitle(projectDto.getTitle());
		        projectExisted.setBudget(projectDto.getBudget());
		        projectExisted.setDateline(projectDto.getDateline());
		        projectExisted.setHoursRequired(projectDto.getHoursRequired());
		        projectExisted.setStatus(projectDto.getStatus());
		        projectExisted.setTechnologies(projectDto.getTechnologies());
		        
		        
			       
			       
				 Project updatedProject = projectRepository.save(projectExisted);
				 return ProjectDto.toDto(updatedProject);
	}
	

}
