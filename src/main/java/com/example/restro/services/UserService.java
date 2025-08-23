package com.example.restro.services;

import com.example.restro.entity.UserDetails;
import com.example.restro.model.RegistrationRequest;
import com.example.restro.model.RegistrationResponse;
import com.example.restro.model.RegistrationUpdateRequest;
import com.example.restro.repository.UserDetailsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserService {

    private UserDetailsRepository userDetailsRepository;

    UserService(UserDetailsRepository userDetailsRepository){
        this.userDetailsRepository = userDetailsRepository;
    }

    public RegistrationResponse createUserRegistration(RegistrationRequest request){
        log.info("Registration request {}", request);

        UserDetails userDetails = UserDetails.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .build();

        userDetailsRepository.save(userDetails);

        return RegistrationResponse.builder()
                .name(userDetails.getName())
                .email(userDetails.getEmail())
                .password(userDetails.getPassword())
                .build();
    }

    public RegistrationResponse updateUserRegistration(RegistrationUpdateRequest request) {
        UserDetails userDetails = userDetailsRepository.findByName(request.getName());

        userDetails.setName(request.getName());
        userDetails.setPassword(request.getPassword());

        userDetailsRepository.save(userDetails);

        return RegistrationResponse.builder()
                .name(userDetails.getName())
                .email(userDetails.getEmail())
                .password(userDetails.getPassword())
                .build();
    }

    public RegistrationResponse deleteUserRegistration(String name) {
        UserDetails userDetails = userDetailsRepository.findByName(name);
        userDetailsRepository.delete(userDetails);

        return RegistrationResponse.builder()
                .name(userDetails.getName())
                .email(userDetails.getEmail())
                .password(userDetails.getPassword())
                .build();

    }

    public List<UserDetails> getAllUserDetails() {
        return userDetailsRepository.findAll();
    }

}

