package com.fr.testairfrance.usermanagement.service.impl;

import com.fr.testairfrance.usermanagement.exception.BadRequestException;
import com.fr.testairfrance.usermanagement.exception.UserAlreadyExistException;
import com.fr.testairfrance.usermanagement.exception.UserNotFrenchOrUnderAgeException;
import com.fr.testairfrance.usermanagement.model.User;
import com.fr.testairfrance.usermanagement.repository.IUserRepository;
import com.fr.testairfrance.usermanagement.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

@Service
public class UserServiceImpl implements IUserService{

    @Autowired
    IUserRepository userRepository;

    private static final String FRANCE = "France";

    @Override
    public User getUser(String name, String birthDate) throws BadRequestException {
        if (name.isEmpty() || birthDate.isEmpty()) {
            throw new BadRequestException("One of the parameters is not present or in a bad format");
        }
        return userRepository.getUser(name, birthDate);
    }

    @Override
    public String registerUser(User user) throws UserAlreadyExistException, UserNotFrenchOrUnderAgeException  {
        if (user == null) {throw new BadRequestException();}
        //Verify if the user is already registered or not
        if (getUser(user.getName(), user.getBirthDate()) == null) {
            //Only adult French residents are allowed to create an account
            if (!FRANCE.equalsIgnoreCase(user.getCountryOfResidence()) || !isAdulte(user.getBirthDate())) {
                throw new UserNotFrenchOrUnderAgeException();
            }
            return userRepository.registerUser(user) ? "User Created!" : "Unable to create user";
        } else {
            throw new UserAlreadyExistException();
        }
    }

    private boolean isAdulte(String birthDate){
        return Period.between(LocalDate.parse(birthDate), LocalDate.now()).getYears() > 18;
    }
}
