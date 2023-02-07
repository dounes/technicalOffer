package com.fr.technicaltestoffer.service.impl;

import com.fr.technicaltestoffer.entity.User;
import com.fr.technicaltestoffer.mapper.UserMapper;
import com.fr.technicaltestoffer.repository.IUserRepository;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@DataJpaTest
class UserServiceTest {

    @Mock
    IUserRepository userRepository;

    @InjectMocks
    UserService userService;

    @Mock
    private UserMapper userMapper;

    EasyRandom generator = new EasyRandom();


    @DisplayName("Should return a user")
    @Test
    void shouldReturnUserbyHisID() {
        User user = generator.nextObject(User.class);
        when(userRepository.findById(2)).thenReturn(Optional.ofNullable(user));

        Optional<User> userFromService = userService.getUser(2);

        Assertions.assertEquals(user, userFromService.get());
        Assertions.assertEquals(user.getFullname(), userFromService.get().getFullname());
        verify(userRepository, times(1)).findById(2);
    }

    @DisplayName("Should create a user")
    @Test
    @Transactional
    void shouldSaveUser() {
        User user = new User();
        //Only adult French residents are allowed to create an account
        user.setUserID(10);
        user.setCountry("FRANCE");
        user.setFullname("Blablablablabla");
        user.setBirthdate(LocalDate.of(1991, 05, 12));

       when(userRepository.save(user)).thenReturn(user);

        User userFromService = userService.saveUser(UserMapper.INSTANCE.mapEntity2DTO(user));
    }
}