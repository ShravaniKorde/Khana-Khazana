package com.example.restro.services;

import com.example.restro.repository.UserDetailsRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserDetailsRepository userDetailsRepository;

    UserService(UserDetailsRepository userDetailsRepository){
        this.userDetailsRepository = userDetailsRepository;
    }
}
