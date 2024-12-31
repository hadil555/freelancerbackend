package tn.essat.entities;

import java.util.List;

import jakarta.persistence.*;
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

public class Skills {


	@Id
	@GeneratedValue

	private Long id;
	private String nom;
	
	@ManyToMany
	private List<Profil> profil;


}
