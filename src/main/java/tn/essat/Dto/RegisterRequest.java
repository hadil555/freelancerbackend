package tn.essat.Dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import tn.essat.entities.Role;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder

public class RegisterRequest {
	private Long id;
	private String nom;
	private String username;
	private String email;
	private String password;
	private Role role;

	 

}
