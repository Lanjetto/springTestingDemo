package com.nexign.testUserService.model;


import lombok.*;

@Getter
@EqualsAndHashCode
public class User {
    private final Long id;
    private final String login;

    public User(Long id, String login) {
        this.id = id;
        this.login = login;
        }

    @Override
    public String toString() {
        return "Пользователь: " +
                id + " "  + login;
    }
}
