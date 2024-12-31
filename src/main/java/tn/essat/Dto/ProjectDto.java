package tn.essat.Dto;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.essat.entities.Profil;
import tn.essat.entities.Project;
import tn.essat.entities.ProjectStatus;
import tn.essat.entities.User;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectDto {
	private Long id;
	private String title;
	private String discription;
    @JsonFormat( pattern = "yyyy-MM-dd")
    private Date dateline;
    private double budget;
    private int hoursRequired;
    private String technologies;
    private ProjectStatus status;

	public static Project toEntity(ProjectDto projectdto) {
        return Project.builder()
                
                .title(projectdto.getTitle())
                .discription(projectdto.getDiscription())
                .budget(projectdto.getBudget())
                .hoursRequired(projectdto.getHoursRequired())
                .technologies(projectdto.getTechnologies())
                .status(projectdto.getStatus())
                .build();
    };
    public static ProjectDto toDto(Project project) {
        return  ProjectDto.builder()
                .id(project.getId())
        		.title(project.getTitle())
                .discription(project.getDiscription())
                .budget(project.getBudget())
                .hoursRequired(project.getHoursRequired())
                .technologies(project.getTechnologies())
                .status(project.getStatus())
                .build();
                
    };

}
