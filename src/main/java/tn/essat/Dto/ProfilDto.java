package tn.essat.Dto;

import java.util.Date;
import java.util.List;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.essat.entities.Message;
import tn.essat.entities.Profil;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfilDto {
	private Long id;
	private String bio;
	private int yearsofexperience;
	private double rating;
	private Long idusers;
	private List<Long> idskills;
	
	public static Profil toEntity(ProfilDto profildto) {
        return Profil.builder()
                
                .bio(profildto.getBio())
                .yearsofexperience(profildto.getYearsofexperience())
                .rating(profildto.getRating())
                .build();
    };
    public static ProfilDto toDto(Profil profil) {
        return  ProfilDto.builder()
                .id(profil.getId())
                .bio(profil.getBio())
                .yearsofexperience(profil.getYearsofexperience())
                .rating(profil.getRating())
                .build();
                
    };
}
