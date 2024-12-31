package tn.essat.Controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.essat.Dto.ProfilDto;
import tn.essat.exception.ProfilNotFoundException;
import tn.essat.service.ProfilService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/profils")
@RequiredArgsConstructor
@Log
public class ProfilController {

    private final ProfilService profilService;

    // Ajouter un profil
    @PostMapping("/add")
    public ResponseEntity<ProfilDto> addProfil(@RequestBody ProfilDto profilDto) {
    	try {
    	  return new ResponseEntity<>( profilService.addProfil(profilDto),HttpStatus.CREATED);
    		
    	}catch(ProfilNotFoundException ex){
    		return new  ResponseEntity<>(HttpStatus.NOT_FOUND);
    	}
    	
    	
    	
    	
        
       
    }

    // Récupérer un profil par ID
    @GetMapping("/getById/{id}")
    public ResponseEntity<ProfilDto> getProfilById(@PathVariable("id") Long id) {
    	try {
    	            return new ResponseEntity<>(profilService.getProfilById(id), HttpStatus.OK); // 200 OK
    	        } catch (ProfilNotFoundException e) {
    	            log.info(String.format("Profil with id = %s not found", id));
    	            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 NOT_FOUND
    	        }
    }

    // Récupérer tous les profils
    @GetMapping("/getAllProfils")
    public ResponseEntity<List<ProfilDto>> getAllProfils() {
        return new ResponseEntity<>(profilService.getAllProfil(),HttpStatus.OK);
    }

    // Mettre à jour un profil par ID
    @PutMapping("/update/{id}")
    public ResponseEntity<ProfilDto> updateProfil(@PathVariable Long id, @RequestBody ProfilDto profilDto) {
    	try {
            ProfilDto updatedProfil = profilService.updateProfilDto(id, profilDto);
            return ResponseEntity.ok(updatedProfil);
        } catch (ProfilNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Supprimer un profil par ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfil(@PathVariable Long id) {
    	try {
            profilService.deleteProfilById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ProfilNotFoundException e) {
            log.info(String.format("Profil with id = %s not found", id));
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 NOT_FOUND
        }
        
        
    }
}
