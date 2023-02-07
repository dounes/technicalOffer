package com.fr.technicaltestoffer.service.impl;

import com.fr.technicaltestoffer.dto.UserDTO;
import com.fr.technicaltestoffer.entity.User;
import com.fr.technicaltestoffer.exception.BadRequestException;
import com.fr.technicaltestoffer.exception.UserAlreadyExistException;
import com.fr.technicaltestoffer.exception.UserNotFrenchOrUnderAgeException;
import com.fr.technicaltestoffer.mapper.UserMapper;
import com.fr.technicaltestoffer.repository.IUserRepository;
import com.fr.technicaltestoffer.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

@Service
public class UserService implements IUserService {

	IUserRepository userRepository;

	private static final String FRANCE = "France";

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	public UserService(IUserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public Optional<User> getUser(Integer userID) throws BadRequestException {
		if (userID == null) {
			throw new BadRequestException("One of the parameters is not present or in a bad format");
		}
		return userRepository.findById(userID);
	}

	@Override
	public User saveUser(UserDTO user) throws UserAlreadyExistException, UserNotFrenchOrUnderAgeException {
		if (user == null) {throw new BadRequestException();}
		//Verify if the user is already registered or not
		if (getUser(user.getUserID()).isEmpty()) {
			//Only adult French residents are allowed to create an account
			if (!FRANCE.equalsIgnoreCase(user.getCountry()) || !isAdulte(user.getBirthdate())) {
				throw new UserNotFrenchOrUnderAgeException();
			}
			LOGGER.info("User " + user.getFullname() + " born on "+ user.getBirthdate() +" is successfully registered");
			return userRepository.save(UserMapper.INSTANCE.mapDTO2Entity(user));
		} else {
			//The user is already registered
			throw new UserAlreadyExistException();
		}
	}

	private boolean isAdulte(LocalDate birthDate){
		return Period.between(birthDate, LocalDate.now()).getYears() >= 18;
	}
}
