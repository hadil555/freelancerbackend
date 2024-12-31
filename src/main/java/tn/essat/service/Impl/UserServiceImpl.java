package tn.essat.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.essat.Dto.FreelancerDto;
import tn.essat.Dto.UserDto;
import tn.essat.Repository.FreelancerRepository;
import tn.essat.service.UserServicee;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserServicee {

    private final FreelancerRepository freelancerRepository;


    @Override
    public List<UserDto> getAllFreelancer() {
        return freelancerRepository.findFreelancers().stream()
                .map(UserDto::toDto)
                .collect(Collectors.toList());
    }
}
