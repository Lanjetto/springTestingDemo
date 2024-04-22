package com.nexign.testUserService.service;


import com.nexign.testUserService.entity.UserEntity;
import com.nexign.testUserService.model.User;
import com.nexign.testUserService.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;

    @Test
    void loginSuccessIfUserExist() {
        userService = new UserService(userRepository);
        User user = new User(1L, "Alex");
        UserEntity userEntity = new UserEntity(1L, "Alex", "123");
        userService.addUsers(userEntity);
        when(userRepository.findAll()).thenReturn(List.of(userEntity));
        Optional<User> optionalUser = userService.login(userEntity.getLogin(), userEntity.getPassword());
        assertThat(optionalUser.isPresent()).isTrue();
        optionalUser.ifPresent(user1 -> assertThat(user1).isEqualTo(user));
    }
}
