package tn.essat.service.Impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import tn.essat.Dto.ProjectDto;
import tn.essat.Dto.PruposalDto;
import tn.essat.Repository.ProjectRepository;
import tn.essat.Repository.PruposalRepository;
import tn.essat.Repository.UserRepository;
import tn.essat.entities.Project;
import tn.essat.entities.Pruposal;
import tn.essat.entities.User;
import tn.essat.service.PruposalService;
@Service
@RequiredArgsConstructor
public class PruposalServiceImpl implements PruposalService {
	private final PruposalRepository pruposalRepository;
	private final UserRepository userRepository;
	private final ProjectRepository projectRepository;
	@Override
	public PruposalDto addPruposal(PruposalDto pruposalDto , Authentication connectedUser) {
		Pruposal pruposal= PruposalDto.toEntity(pruposalDto);
		User user = ((User) connectedUser.getPrincipal());

		Optional<User>userOptional=userRepository.findById(user.getId());
		Optional<Project>projectOptional=projectRepository.findById(pruposalDto.getIdproject());
		
		if(userOptional.isPresent()&& projectOptional.isPresent()) {
			pruposal.setFreelancer(userOptional.get());
			pruposal.setProject(projectOptional.get());
			Pruposal pruposalsaved= pruposalRepository.save(pruposal);
			return PruposalDto.toDto(pruposalsaved);
		}else {
			throw new RuntimeException("notfound");
		}
	}
	@Override
	public PruposalDto getPruposalById(Long id) {
		Optional<Pruposal>pruposal = pruposalRepository.findById(id);
		if(pruposal.isPresent()) {
			return PruposalDto.toDto(pruposal.get());
		}else {
			throw new RuntimeException("notfound");
		}
	}
	@Override
	public List<PruposalDto> getAllPruposals() {
		return pruposalRepository.findAll().stream()
				.map(PruposalDto::toDto)
				.collect(Collectors.toList());
	}
	@Override
	public void deletePruposalById(Long id) {
		getPruposalById(id);
		pruposalRepository.deleteById(id);
		
	}
	@Override
	public PruposalDto updatePruposal(Long id, PruposalDto pruposalDto) {
		 Pruposal pruposalExisted = pruposalRepository.findById(id)
				 .orElseThrow(RuntimeException:: new);
			       
		        pruposalExisted.setAmount(pruposalDto.getAmount());
		        pruposalExisted.setDescription(pruposalDto.getDescription());
		        
		       
		        
			       
			       
				 Pruposal updatedPruposal = pruposalRepository.save(pruposalExisted);
				 return PruposalDto.toDto(updatedPruposal);
	}
	

}
