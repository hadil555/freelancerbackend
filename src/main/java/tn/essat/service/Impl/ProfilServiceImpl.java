package tn.essat.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import tn.essat.Dto.MessageDto;
import tn.essat.Dto.ProfilDto;
import tn.essat.Repository.FreelancerRepository;
import tn.essat.Repository.ProfilRepository;
import tn.essat.Repository.SkillsRepository;
import tn.essat.Repository.UserRepository;
import tn.essat.entities.Freelancer;
import tn.essat.entities.Message;
import tn.essat.entities.Profil;
import tn.essat.entities.Skills;
import tn.essat.entities.User;
import tn.essat.service.ProfilService;
@Service
@RequiredArgsConstructor
public class ProfilServiceImpl implements ProfilService {
	@Lazy
	private final ProfilRepository profilRepository;
	private final FreelancerRepository freelancerRepository;
	private final SkillsRepository skillsRepository;

	@Override
	public ProfilDto addProfil(ProfilDto profilDto) {
		Profil profil=ProfilDto.toEntity(profilDto);
		Optional<Freelancer>freeOptional=freelancerRepository.findById(profilDto.getIdusers());
		 List<Skills> skills= new ArrayList<>();
	       if (profilDto.getIdskills().isEmpty()) {
	           throw new IllegalArgumentException("you need atleast one skills");
	       } else {
	           for (Long idskils : profilDto.getIdskills()) {
	               Optional<Skills> skill = skillsRepository.findById(idskils);
	               skill.ifPresent(skills::add);
	           }
	       }
		
		if(freeOptional.isPresent()) {
			profil.setFreelancer(freeOptional.get());
			profil.setSkills(skills);
			Profil profilsaved= profilRepository.save(profil);
			return ProfilDto.toDto(profilsaved);
		}else {
			throw new RuntimeException("notfound");
		}
		
	}

	@Override
	public ProfilDto getProfilById(Long id) {
		Optional<Profil>profil = profilRepository.findById(id);
		if(profil.isPresent()) {
			return ProfilDto.toDto(profil.get());
		}else {
			throw new RuntimeException("notfound");
		}
		
	}

	@Override
	public List<ProfilDto> getAllProfil() {
		return profilRepository.findAll().stream()
				.map(ProfilDto::toDto)
				.collect(Collectors.toList());
	}

	@Override
	public void deleteProfilById(Long id) {
		getProfilById(id);
		profilRepository.deleteById(id);
		
	}

	@Override
	public ProfilDto updateProfilDto(Long id, ProfilDto profilDto) {
		 Profil profilExisted = profilRepository.findById(id)
				 .orElseThrow(RuntimeException:: new);
			       
		        profilExisted.setBio(profilDto.getBio());
		        profilExisted.setYearsofexperience(profilDto.getYearsofexperience());
		        profilExisted.setRating(profilDto.getRating());
			       
			       
				 Profil updatedProfil = profilRepository.save(profilExisted);
				 return ProfilDto.toDto(updatedProfil);
	}
	
	

}
