package tn.essat.service;


import tn.essat.Dto.SkillsDto;
import tn.essat.exception.SkillNotFoundException;


import java.util.List;

public interface SkillsService {
    SkillsDto addSkill(SkillsDto projectDto)throws SkillNotFoundException;

    SkillsDto getSkillById(Long id) throws SkillNotFoundException;

    List<SkillsDto> getAllSkills();

    void deleteSkillById(Long id) throws SkillNotFoundException;

    SkillsDto updateSkill(Long id, SkillsDto SkillsDto)throws SkillNotFoundException;

}
