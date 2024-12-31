package tn.essat.service;

import java.util.List;

import tn.essat.Dto.MessageDto;
import tn.essat.Dto.ProfilDto;
import tn.essat.exception.ProfilNotFoundException;

public interface ProfilService {
	ProfilDto addProfil(ProfilDto profilDto)throws ProfilNotFoundException;

	ProfilDto getProfilById(Long id)throws ProfilNotFoundException ;

    List<ProfilDto> getAllProfil();

    void deleteProfilById(Long id) throws ProfilNotFoundException;

    ProfilDto updateProfilDto(Long id, ProfilDto profilDto)throws ProfilNotFoundException;


}
