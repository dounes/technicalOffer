package com.fr.technicaltestoffer.usermanagement.service;

import com.fr.technicaltestoffer.usermanagement.exception.BadRequestException;
import com.fr.technicaltestoffer.usermanagement.exception.UserAlreadyExistException;
import com.fr.technicaltestoffer.usermanagement.exception.UserNotFrenchOrUnderAgeException;
import com.fr.technicaltestoffer.usermanagement.model.User;

public interface IUserService {

    //Retrieve a user by his name and birthdate
    User getUser(String name, String birthDate) throws BadRequestException;
    //Register a user by his name and birthdate
    String registerUser(User user) throws UserAlreadyExistException, UserNotFrenchOrUnderAgeException;
}
