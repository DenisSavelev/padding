package com.diplom.padding.entity.app;

import com.diplom.padding.entity.moodle.UserMoodle;

import javax.persistence.*;

@Entity
@Table(schema = "public")
public class User {
    @Id
    private Long id;
    private String surname;
    private String name;
    private String patronymic;
    private String login;
    private String password;
    private String email;

    public User() {
    }

    public User(UserMoodle userMoodle) {
        this.id = userMoodle.getId();
        this.surname = userMoodle.getSurname();
        this.name = userMoodle.getName();
        this.patronymic = userMoodle.getPatronymic();
        this.login = userMoodle.getLogin();
        this.password = userMoodle.getPassword();
        this.email = userMoodle.getEmail();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}