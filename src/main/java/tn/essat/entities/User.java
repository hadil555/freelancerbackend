package tn.essat.entities;

import java.util.Collection;
import java.util.List;

import org.springframework.security.config.annotation.authentication.configurers.userdetails.UserDetailsAwareConfigurer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import tn.essat.*;
import tn.essat.*;

import static org.aspectj.weaver.tools.cache.SimpleCacheFactory.enabled;

@Data
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)


public class User  implements UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	private Long id;
	private String nom;
	private String username;
	@Column(unique = true, nullable = false)
	private String email;
	private String password;
	@Enumerated(EnumType.STRING)
	private Role role;
	 @ManyToOne(fetch = FetchType.LAZY)
	 @JsonBackReference
	 private Project project;
	 @ManyToOne(fetch = FetchType.LAZY)
	 @JsonBackReference
	 private Pruposal pruposal;
	 
	 @OneToMany(fetch = FetchType.LAZY)
	 @JsonBackReference
	 private List<Message>mesg;
	 @Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
		       return List.of(new SimpleGrantedAuthority(role.name()));
		   }
		@Override
		public String getPassword() {
			// TODO Auto-generated method stub
			return password;
		}
		@Override
		public String getUsername() {
			// TODO Auto-generated method stub
			return email;
		}
		public Object stream() {
			// TODO Auto-generated method stub
			return null;
		}
	@Override
	public boolean isEnabled() {
		return true;
	}
	 
	

}
