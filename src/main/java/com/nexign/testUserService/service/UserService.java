package com.nexign.testUserService.service;

import com.nexign.testUserService.entity.UserEntity;
import com.nexign.testUserService.model.User;
import com.nexign.testUserService.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addUsers(UserEntity...users) {
        userRepository.saveAll(Arrays.asList(users));
    }

    public List<User> getAllUsers() {
        List<UserEntity> usersEntities = userRepository.findAll();
        return usersEntities
                .stream()
                .map(user -> new User(user.getId(), user.getLogin()))
                .collect(Collectors.toList());
    }

    public Optional<User> login(String login, String password) {
        if (login == null || password == null) {
            throw new IllegalArgumentException("login or pass is null");
        }
        return userRepository.findAll().stream()
                .filter(user -> user.getLogin().equals(login))
                .filter(user -> user.getPassword().equals(password))
                .findFirst()
                .map(user -> new User(user.getId(), user.getLogin()));
    }

    public Map<Long, User> getUserMap() {
        return userRepository.findAll().stream()
                .collect(Collectors.toMap(UserEntity::getId, user -> new User(user.getId(), user.getLogin())));
    }
}
