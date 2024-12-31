package tn.essat.service;

import java.util.List;

import org.springframework.security.core.Authentication;
import tn.essat.Dto.ProjectDto;
import tn.essat.Dto.PruposalDto;
import tn.essat.exception.PruposalNotFoundException;

public interface PruposalService {
	PruposalDto addPruposal(PruposalDto projectDto  , Authentication connectedUser)throws PruposalNotFoundException;

	PruposalDto getPruposalById(Long id) throws PruposalNotFoundException;

    List<PruposalDto> getAllPruposals();

    void deletePruposalById(Long id) throws PruposalNotFoundException;

    PruposalDto updatePruposal(Long id, PruposalDto PruposalDto)throws PruposalNotFoundException;


}
