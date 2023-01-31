package com.fr.technicaltestoffer.usermanagement.service.impl;

import com.fr.technicaltestoffer.usermanagement.exception.BadRequestException;
import com.fr.technicaltestoffer.usermanagement.exception.UserAlreadyExistException;
import com.fr.technicaltestoffer.usermanagement.exception.UserNotFrenchOrUnderAgeException;
import com.fr.technicaltestoffer.usermanagement.model.User;
import com.fr.technicaltestoffer.usermanagement.repository.IUserRepository;
import com.fr.technicaltestoffer.usermanagement.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

@Service
public class UserServiceImpl implements IUserService{

    @Autowired
    IUserRepository userRepository;

    private static final String FRANCE = "France";

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());


    @Override
    public User getUser(String name, String birthDate) throws BadRequestException {
        if (name.isEmpty() || birthDate.isEmpty()) {
            throw new BadRequestException("One of the parameters is not present or in a bad format");
        }
        return userRepository.getUser(name, birthDate);
    }

    @Override
    public String registerUser(User user) throws BadRequestException, UserAlreadyExistException, UserNotFrenchOrUnderAgeException {
        if (user == null) {throw new BadRequestException();}
        //Verify if the user is already registered or not
        if (getUser(user.getName(), user.getBirthDate()) == null) {
            //Only adult French residents are allowed to create an account
            if (!FRANCE.equalsIgnoreCase(user.getCountryOfResidence()) || !isAdulte(user.getBirthDate())) {
                throw new UserNotFrenchOrUnderAgeException();
            }
            LOGGER.info("User " + user.getName() + " born on "+ user.getBirthDate() +" is successfully registered");
            return userRepository.registerUser(user) ? "User Created!" : "Unable to create user";
        } else {
            throw new UserAlreadyExistException();
        }
    }

    private boolean isAdulte(String birthDate){
        return Period.between(LocalDate.parse(birthDate), LocalDate.now()).getYears() > 18;
    }
}
