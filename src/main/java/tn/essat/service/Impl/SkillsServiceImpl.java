package tn.essat.service.Impl;

import tn.essat.Dto.SkillsDto;
import tn.essat.exception.SkillNotFoundException;
import tn.essat.service.SkillsService;

import java.util.List;

public class SkillsServiceImpl implements SkillsService {


    @Override
    public SkillsDto addSkill(SkillsDto projectDto) throws SkillNotFoundException {
        return null;
    }

    @Override
    public SkillsDto getSkillById(Long id) throws SkillNotFoundException {
        return null;
    }

    @Override
    public List<SkillsDto> getAllSkills() {
        return List.of();
    }

    @Override
    public void deleteSkillById(Long id) throws SkillNotFoundException {

    }

    @Override
    public SkillsDto updateSkill(Long id, SkillsDto SkillsDto) throws SkillNotFoundException {
        return null;
    }
}
