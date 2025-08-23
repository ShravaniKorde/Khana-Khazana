package com.example.restro.controller;

import com.example.restro.entity.UserDetails;
import com.example.restro.model.RegistrationRequest;
import com.example.restro.model.RegistrationResponse;
import com.example.restro.model.RegistrationUpdateRequest;
import com.example.restro.services.UserService;
import org.springframework.boot.autoconfigure.security.saml2.Saml2RelyingPartyProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    UserController(UserService userService){
        this.userService = userService;
    }

//    private List<String> dummyUsers = Arrays.asList(   "ID: 1, Name: John, Email: john@example.com, Password: 1234, Phone: 9876543210",
//            "ID: 2, Name: Doe, Email: doe@example.com, Password: 5678, Phone: 8765432109",
//            "ID: 3, Name: Mark, Email: mark@example.com, Password: abcd, Phone: 7654321098",
//            "ID: 4, Name: Jenny, Email: jenny@example.com, Password: efgh, Phone: 6543210987");
//
//    private List<RegistrationRequest> userList = new ArrayList<>();
//
//    @GetMapping("/test")
//    public ResponseEntity<List<String>> getUserDetails(){
//        return new ResponseEntity<>(dummyUsers, HttpStatus.ACCEPTED);
//    }
//
//    @GetMapping("/test/{ownerName}")
//    public ResponseEntity<String> getUserDetailsFromPath(@PathVariable(name = "ownerName") String name){
//        return new ResponseEntity<>("Hello "+ name + " here is the list " +dummyUsers, HttpStatus.ACCEPTED);
//    }
//
//    @GetMapping("/test/{ownerName}/param")
//    public ResponseEntity<String> getUserDetailsFromParams(@PathVariable(name = "ownerName") String name, @RequestParam("surname") String paramValue){
//        return new ResponseEntity<>("Hello "+ name + " "+paramValue + " here is the list" + dummyUsers, HttpStatus.ACCEPTED);
//    }
//
//    @GetMapping("/test/header")
//    public ResponseEntity<String> getUserWithHeader(@RequestHeader("my-header") String headerValue) {
//        return new ResponseEntity<>("Received header value: " + headerValue + ". Dummy users: " + dummyUsers, HttpStatus.OK);
//    }


    @PostMapping("/create")
    public ResponseEntity<RegistrationResponse> createUserRegistration(@RequestBody RegistrationRequest registrationRequest){
        return new ResponseEntity<>(this.userService.createUserRegistration(registrationRequest), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<RegistrationResponse> updateUserRegistration(@RequestBody RegistrationUpdateRequest registrationUpdateRequest){
        return new ResponseEntity<>(this.userService.updateUserRegistration(registrationUpdateRequest), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{name}")
    public ResponseEntity<RegistrationResponse> deleteUserRegistration(@PathVariable String name){
        return new ResponseEntity<>(this.userService.deleteUserRegistration(name), HttpStatus.OK);
    }

    @GetMapping("/read")
    public ResponseEntity<List<UserDetails>> getAllUserDetails() {
        return new ResponseEntity<>(this.userService.getAllUserDetails(), HttpStatus.OK);
    }


}
