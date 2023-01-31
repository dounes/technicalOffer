package com.fr.technicaltestoffer.usermanagement.controller;

import com.fr.technicaltestoffer.usermanagement.aspect.LogExecutionTime;
import com.fr.technicaltestoffer.usermanagement.model.User;
import com.fr.technicaltestoffer.usermanagement.exception.UserAlreadyExistException;
import com.fr.technicaltestoffer.usermanagement.exception.UserNotFoundException;
import com.fr.technicaltestoffer.usermanagement.exception.UserNotFrenchOrUnderAgeException;
import com.fr.technicaltestoffer.usermanagement.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/users")
@Validated
public class UserController {

    @Autowired
    IUserService userService;

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());


    @GetMapping("/retrieve")
    @LogExecutionTime
    public ResponseEntity<User> retrieveUser(@RequestParam("name") @NotNull String name,
                                             @RequestParam("birthDate") @NotNull String birthDate) throws UserNotFoundException {
        User user = userService.getUser(name, birthDate);
        if(user == null){return ResponseEntity.notFound().build();}
        LOGGER.info("User " + name + " born on "+ birthDate +" is successfully retrieved");
        return  ResponseEntity.ok().body(user);
    }

    @PostMapping("/register")
    @LogExecutionTime
    public ResponseEntity<String> registerUser(@Valid @RequestBody User user) throws UserAlreadyExistException, UserNotFrenchOrUnderAgeException {
        return ResponseEntity.ok().body(userService.registerUser(user));
    }
}
