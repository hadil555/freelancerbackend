package tn.essat.Dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import tn.essat.entities.Freelancer;
import tn.essat.entities.Message;
import tn.essat.entities.Profil;
import tn.essat.entities.Skills;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class FreelancerDto extends RegisterRequest{
	
	private String domaine;
	
	private Long idprofil;
	
	
	public static Freelancer toEntity(FreelancerDto freelancerdto) {
        return Freelancer.builder()
               
                .domaine(freelancerdto.getDomaine())
                .nom(freelancerdto.getNom())
                .username(freelancerdto.getUsername())
                .email(freelancerdto.getEmail())
                .build();
    }
    public static FreelancerDto toDto(Freelancer freelancer) {
        return FreelancerDto.builder()
        		
        		.domaine(freelancer.getDomaine())
        		.id(freelancer.getId())
        		.nom(freelancer.getNom())
        		.email(freelancer.getEmail())
        		
        		.password(freelancer.getPassword())
        		
        		
        		
                .build();
    }

}
