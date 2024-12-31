package tn.essat.entities;


import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
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
public class Profil {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String bio;
	
	private int yearsofexperience;
	private boolean availability;
	private double rating;
    @OneToOne
    @JoinColumn(name = "freelancer_id", referencedColumnName = "id")
	private Freelancer freelancer;
    @ManyToMany
	private List<Skills> skills;
	

	   

}
