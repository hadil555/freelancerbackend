package tn.essat.entities;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Project {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String title;
	private String discription;

	@JsonFormat( pattern = "yyyy-MM-dd")
	private Date dateline;
    private double budget;
    private int hoursRequired;
    private String technologies;
    @Enumerated(EnumType.STRING)
    private ProjectStatus status; // PENDING, IN_PROGRESS, COMPLETED
    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;



}
