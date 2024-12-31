package tn.essat.Dto;


import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.essat.entities.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder



public class UserDto {
	private Long id;
	private String nom;
	private String username;
	private String email;
	private Role role;
	private Set<Project> projects;
	private Long idProfil;




	public static User toEntity(UserDto userDto) {
		return User.builder()

				.nom(userDto.getNom())
				.username(userDto.getUsername())
				.email(userDto.getEmail())
				.role(userDto.getRole())
				.build();
	}
	public static UserDto toDto(User user) {
		return UserDto.builder()

				.id(user.getId())
				.nom(user.getNom())
				.email(user.getEmail())
				.role(user.getRole())

				.build();
	}

}
