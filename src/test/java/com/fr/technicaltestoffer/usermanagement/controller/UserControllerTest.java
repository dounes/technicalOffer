package com.fr.technicaltestoffer.usermanagement.controller;

import com.fr.technicaltestoffer.usermanagement.exception.BadRequestException;
import com.fr.technicaltestoffer.usermanagement.exception.UserAlreadyExistException;
import com.fr.technicaltestoffer.usermanagement.exception.UserNotFrenchOrUnderAgeException;
import com.fr.technicaltestoffer.usermanagement.model.User;
import com.fr.technicaltestoffer.usermanagement.repository.IUserRepository;
import com.fr.technicaltestoffer.usermanagement.service.IUserService;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.OK;

@SpringBootTest
@ActiveProfiles("test")
class UserControllerTest {

    @Mock
    private IUserService userService;

    @Mock
    private IUserRepository userRepository;

    @InjectMocks
    private UserController userController;

    EasyRandom generator = new EasyRandom();
    private static final String name = "Aurore ZAYUD";
    private static final String birthDate = "2021-07-01";
    private static final String USER_CREATED = "User Created!";

    @Value(value = "${exception.message.userNotFrenchOrUnderAgeException}")
    private String userNotFrenchOrUnderAgeException;


    @DisplayName("Should retrieve a user")
    @Test
    void testRetrieveUser() throws Exception {
        User user = generator.nextObject(User.class);

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(userRepository.getUser(name, birthDate)).thenReturn(user);
        when(userService.getUser(name, birthDate)).thenReturn(user);

        ResponseEntity<User> response = userController.retrieveUser(name, birthDate);

        Assertions.assertEquals(OK.value(), response.getStatusCodeValue());
    }

    @DisplayName("Should register a user")
    @Test
    void testRegisterUser() {
        User user = generator.nextObject(User.class);
        //Only adult French residents are allowed to create an account
        user.setCountryOfResidence("FRANCE");
        user.setBirthDate("1998-05-12");

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(userRepository.registerUser(user)).thenReturn(true);
        when(userService.registerUser(user)).thenReturn(USER_CREATED);

        ResponseEntity<String> response = userController.registerUser(user);

        Assertions.assertEquals(OK.value(), response.getStatusCodeValue());
        Assertions.assertEquals(USER_CREATED, response.getBody());
    }

}