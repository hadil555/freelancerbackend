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

import tn.essat.Dto.PruposalDto;

import tn.essat.exception.PruposalNotFoundException;

import tn.essat.service.PruposalService;
@RestController
@RequestMapping("/api/v1/pruposals")
@RequiredArgsConstructor
@Log

public class PruposalController {
	private final PruposalService pruposalService;
	
	  // Ajouter un profil
@PostMapping("/add")
public ResponseEntity<PruposalDto> addPruposal(@RequestBody PruposalDto pruposalDto , Authentication connectedUser) {
	try {
	  return new ResponseEntity<>( pruposalService.addPruposal(pruposalDto , connectedUser),HttpStatus.CREATED);
		
	}catch(PruposalNotFoundException ex){
		return new  ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	
	
	
	
    
   
}

// Récupérer un profil par ID
@GetMapping("/getById/{id}")
public ResponseEntity<PruposalDto> getPruposalById(@PathVariable("id") Long id) {
	try {
	            return new ResponseEntity<>(pruposalService.getPruposalById(id), HttpStatus.OK); // 200 OK
	        } catch (PruposalNotFoundException e) {
	            log.info(String.format("Pruposal with id = %s not found", id));
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 NOT_FOUND
	        }
}

// Récupérer tous les profils
@GetMapping("/getAllPruposal")
public ResponseEntity<List<PruposalDto>> getAllPruposal() {
    return new ResponseEntity<>(pruposalService.getAllPruposals(),HttpStatus.OK);
}

// Mettre à jour un profil par ID
@PutMapping("/update/{id}")
public ResponseEntity<PruposalDto> updatePruposal(@PathVariable Long id, @RequestBody PruposalDto pruposalDto) {
	try {
        PruposalDto updatedPruposal = pruposalService.updatePruposal(id, pruposalDto);
        return ResponseEntity.ok(updatedPruposal);
    } catch (PruposalNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}

// Supprimer un profil par ID
@DeleteMapping("/delete/{id}")
public ResponseEntity<Void> deletePruposal(@PathVariable Long id) {
	try {
        pruposalService.deletePruposalById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    } catch (PruposalNotFoundException e) {
        log.info(String.format("Pruposal with id = %s not found", id));
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 NOT_FOUND
    }
    
    
}

}
