package com.fr.technicaltestoffer.service;

import com.fr.technicaltestoffer.dto.UserDTO;
import com.fr.technicaltestoffer.entity.User;
import com.fr.technicaltestoffer.exception.BadRequestException;
import com.fr.technicaltestoffer.exception.UserAlreadyExistException;
import com.fr.technicaltestoffer.exception.UserNotFrenchOrUnderAgeException;

import java.util.Optional;

public interface IUserService {
	Optional<User> getUser(Integer userID) throws BadRequestException;
	User saveUser(UserDTO user) throws UserAlreadyExistException, UserNotFrenchOrUnderAgeException;
}
