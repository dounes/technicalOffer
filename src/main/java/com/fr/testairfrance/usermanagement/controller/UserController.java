package com.fr.testairfrance.usermanagement.controller;

import com.fr.testairfrance.usermanagement.aspect.LogExecutionTime;
import com.fr.testairfrance.usermanagement.exception.UserAlreadyExistException;
import com.fr.testairfrance.usermanagement.exception.UserNotFoundException;
import com.fr.testairfrance.usermanagement.exception.UserNotFrenchOrUnderAgeException;
import com.fr.testairfrance.usermanagement.model.User;
import com.fr.testairfrance.usermanagement.service.IUserService;
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

    @GetMapping("/retrieve")
    @LogExecutionTime
    public ResponseEntity<User> retrieveUser(@RequestParam("name") @NotNull String name,
                                             @RequestParam("birthDate") @NotNull String birthDate) throws UserNotFoundException {
        User user = userService.getUser(name, birthDate);
        if(user == null){return ResponseEntity.notFound().build();}
        return  ResponseEntity.ok().body(user);
    }

    @PostMapping("/register")
    @LogExecutionTime
    public ResponseEntity<String> registerUser(@Valid @RequestBody User user) throws UserAlreadyExistException, UserNotFrenchOrUnderAgeException {
        return ResponseEntity.ok().body(userService.registerUser(user));
    }
}
