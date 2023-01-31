package com.fr.technicaltestoffer.usermanagement.service.impl;

import com.fr.technicaltestoffer.usermanagement.exception.BadRequestException;
import com.fr.technicaltestoffer.usermanagement.exception.UserAlreadyExistException;
import com.fr.technicaltestoffer.usermanagement.exception.UserNotFrenchOrUnderAgeException;
import com.fr.technicaltestoffer.usermanagement.model.User;
import com.fr.technicaltestoffer.usermanagement.repository.IUserRepository;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
class UserServiceImplTest {

    @Mock
    IUserRepository userRepository;

    @InjectMocks
    UserServiceImpl userService;

    @Value(value = "${exception.message.userNotFrenchOrUnderAgeException}")
    private String userNotFrenchOrUnderAgeException;

    //Dummu object generator for test
    EasyRandom generator = new EasyRandom();


    private static final String name = "Aurore ZAYUD";
    private static final String birthDate = "2021-07-01";
    private static final String USER_CREATED = "User Created!";
    private static final String USER_NOT_CREATED = "Unable to create user";

    @DisplayName("Should return a user")
    @Test
    void getUser() {
        User user = generator.nextObject(User.class);
        when(userRepository.getUser(name,birthDate)).thenReturn(user);

        User userFromService = userService.getUser(name,birthDate);

        Assertions.assertEquals(user, userFromService);
        verify(userRepository, times(1)).getUser(name,birthDate);
    }

    @DisplayName("Should create a user")
    @Test
    void registerUserOK() {
        User user = generator.nextObject(User.class);
        //Only adult French residents are allowed to create an account
        user.setCountryOfResidence("FRANCE");
        user.setBirthDate("1998-05-12");

        when(userRepository.registerUser(user)).thenReturn(true);

        String isCreated = userService.registerUser(user);
        Assertions.assertEquals(USER_CREATED, isCreated);
        verify(userRepository, times(1)).registerUser(user);
    }
}