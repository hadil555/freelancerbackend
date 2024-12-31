package tn.essat.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;


import tn.essat.Dto.ProjectDto;


import tn.essat.exception.ProjectNotFoundException;

import tn.essat.service.ProjectService;

@RestController
@RequestMapping("/api/v1/projects")
@RequiredArgsConstructor
@Log
public class ProjectController {
	
	private final ProjectService projectService;
	
	  
  @PostMapping("/add")
  public ResponseEntity<ProjectDto> addProject(@RequestBody ProjectDto projectDto , Authentication authentication) {
  	try {
  	  return new ResponseEntity<>( projectService.addProjet(projectDto ,authentication),HttpStatus.CREATED);
  		
  	}catch(ProjectNotFoundException ex){
  		return new  ResponseEntity<>(HttpStatus.NOT_FOUND);
  	}
  	
  	
  	
  	
  	
      
     
  }

  
  @GetMapping("/getById/{id}")
  public ResponseEntity<ProjectDto> getProjectById(@PathVariable("id") Long id) {
  	try {
  	            return new ResponseEntity<>(projectService.getProjecctById(id), HttpStatus.OK); // 200 OK
  	        } catch (ProjectNotFoundException e) {
  	            log.info(String.format("Project with id = %s not found", id));
  	            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 NOT_FOUND
  	        }
  }

  
  @GetMapping("/getAllProjects")
  public ResponseEntity<List<ProjectDto>> getAllProjects() {
      return new ResponseEntity<>(projectService.getAllProjets(),HttpStatus.OK);
  }

  
  @PutMapping("/update/{id}")
  public ResponseEntity<ProjectDto> updateProject(@PathVariable Long id, @RequestBody ProjectDto projectDto) {
  	try {
          ProjectDto updatedProject = projectService.updateProjet(id, projectDto);
          return ResponseEntity.ok(updatedProject);
      } catch (ProjectNotFoundException e) {
          return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
      }
  }

  
  @DeleteMapping("/delete/{id}")
  public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
  	try {
          projectService.deleteProjectById(id);
          return new ResponseEntity<>(HttpStatus.OK);
      } catch (ProjectNotFoundException e) {
          log.info(String.format("Project with id = %s not found", id));
          return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 NOT_FOUND
      }
      
      
  }
	
	

}
