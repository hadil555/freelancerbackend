package tn.essat.Dto;

import java.util.Date;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import lombok.NoArgsConstructor;

import tn.essat.entities.Project;
import tn.essat.entities.ProjectStatus;
import tn.essat.entities.Pruposal;
import tn.essat.entities.User;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder


public class PruposalDto {
	private Long id;
	private double amount;
	private String description;
	private Long  idproject;
	private Long  idfreelancer;
	
	public static Pruposal toEntity(PruposalDto pruposaldto) {
        return Pruposal.builder()
				.id(pruposaldto.getId())
                .amount(pruposaldto.getAmount())
                .description(pruposaldto.getDescription())
                .build();
                
    }
	public static PruposalDto toDto(Pruposal pruposal) {
        return PruposalDto.builder()
        		 .id(pruposal.getId())
                
                .amount(pruposal.getAmount())
                .description(pruposal.getDescription())
                .build();
                
   
                
   
	

}
}
