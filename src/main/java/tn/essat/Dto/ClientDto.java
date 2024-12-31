package tn.essat.Dto;

import java.util.List;


import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.essat.entities.Client;
import tn.essat.entities.Freelancer;
import tn.essat.entities.Project;
import tn.essat.entities.Skills;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder

public class ClientDto extends RegisterRequest {
	private String numtelephone;
	private List<Long>idproject;
	
	public static Client toEntity(ClientDto clientdto) {
        return Client.builder()
               
                
                .nom(clientdto.getNom())
                .username(clientdto.getUsername())
                .email(clientdto.getEmail())
                .build();
    }
    public static ClientDto toDto(Client client) {
        return ClientDto.builder()
        		.numtelephone(client.getNumtelephone())
        		.nom(client.getNom())
                .username(client.getUsername())
                .email(client.getEmail())
                .build();
    }

	
	
	
	
}
