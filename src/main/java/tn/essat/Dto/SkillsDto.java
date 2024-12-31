package tn.essat.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tn.essat.entities.Profil;
import tn.essat.entities.Skills;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SkillsDto {
    private Long id;
    private String nom;
    public static Skills toEntity(SkillsDto skillsDtodto) {
        return Skills.builder()

                .nom(skillsDtodto.getNom())

                .build();
    };
    public static SkillsDto toDto(Skills skills) {
        return  SkillsDto.builder()
                .id(skills.getId())
                .nom(skills.getNom())

                .build();

    };
}







