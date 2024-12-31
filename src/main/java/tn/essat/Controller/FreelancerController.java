package tn.essat.Controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.essat.Dto.FreelancerDto;
import tn.essat.Dto.UserDto;
import tn.essat.service.UserServicee;

import java.util.List;

@RestController
@RequestMapping("/api/v1/freelancer")
@RequiredArgsConstructor

public class FreelancerController {

    private final UserServicee userServicee;


    @GetMapping("/getallfreelancer")
    public List<UserDto> getAllFreelancer() {
        return userServicee.getAllFreelancer();
    }
}
