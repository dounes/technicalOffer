package com.fr.testairfrance.usermanagement.service;

import com.fr.testairfrance.usermanagement.exception.BadRequestException;
import com.fr.testairfrance.usermanagement.exception.UserAlreadyExistException;
import com.fr.testairfrance.usermanagement.exception.UserNotFrenchOrUnderAgeException;
import com.fr.testairfrance.usermanagement.model.User;

public interface IUserService {

    User getUser(String name, String birthDate) throws BadRequestException;
    String registerUser(User user) throws UserAlreadyExistException, UserNotFrenchOrUnderAgeException;
}
