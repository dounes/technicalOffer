package com.fr.technicaltestoffer.controller;

import com.fr.technicaltestoffer.dto.UserDTO;
import com.fr.technicaltestoffer.entity.User;
import com.fr.technicaltestoffer.mapper.UserMapper;
import com.fr.technicaltestoffer.repository.IUserRepository;
import com.fr.technicaltestoffer.service.IUserService;
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

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.OK;

@SpringBootTest
@ActiveProfiles("test")
class UserControllerTest {

    @Mock
    private IUserService userService;

    @Mock
    private IUserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserController userController;

    EasyRandom generator = new EasyRandom();

    @DisplayName("Should retrieve a user")
    @Test
    void testGetUser() throws Exception {
        User user = generator.nextObject(User.class);

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(userRepository.findById(1)).thenReturn(Optional.ofNullable(user));
        when(userService.getUser(1)).thenReturn(Optional.ofNullable(user));

        ResponseEntity<UserDTO> response = userController.getUser(1);

        Assertions.assertEquals(OK.value(), response.getStatusCodeValue());
        Assertions.assertEquals(user.getUserID(), response.getBody().getUserID());
    }

    @DisplayName("Should register a user")
    @Test
    void testRegisterUser() {
        User user = generator.nextObject(User.class);
        UserDTO userDTO = generator.nextObject(UserDTO.class);
        //Only adult French residents are allowed to create an account
        user.setCountry("FRANCE");
        user.setFullname("John Cena");
        user.setBirthdate(LocalDate.of(1998, 05, 12));

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(userMapper.mapEntity2DTO(user)).thenReturn(userDTO);
        when(userRepository.findById(user.getUserID())).thenReturn(Optional.of(user));
        when(userService.saveUser(userDTO)).thenReturn(user);

        ResponseEntity<UserDTO> response = userController.saveUser(userMapper.mapEntity2DTO(user));

        Assertions.assertEquals(OK.value(), response.getStatusCodeValue());
    }

}

