package com.example.restro.controller;

import com.example.restro.entity.UserDetails;
import com.example.restro.model.RegistrationRequest;
import com.example.restro.model.RegistrationResponse;
import com.example.restro.model.RegistrationUpdateRequest;
import com.example.restro.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<RegistrationResponse> createUserRegistration(@RequestBody RegistrationRequest registrationRequest){
        return new ResponseEntity<>(this.userService.createUserRegistration(registrationRequest), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<RegistrationResponse> updateUserRegistration(@RequestBody RegistrationUpdateRequest registrationUpdateRequest){
        return new ResponseEntity<>(this.userService.updateUserRegistration(registrationUpdateRequest), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{email}")
    public ResponseEntity<RegistrationResponse> deleteUserRegistration(@PathVariable String email){
        return new ResponseEntity<>(this.userService.deleteUserRegistration(email), HttpStatus.OK);
    }

    @GetMapping("/read")
    public ResponseEntity<List<UserDetails>> getAllUserDetails() {
        return new ResponseEntity<>(this.userService.getAllUserDetails(), HttpStatus.OK);
    }


}
