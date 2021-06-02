package com.diplom.padding.entity.moodle;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class UserMoodle {
    @Id
    private Long id;
    @Column(name = "lastname")
    private String surname;
    @Column(name = "firstname")
    private String name;
    @Column(name = "middlename")
    private String patronymic;
    @Column(name = "username")
    private String login;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;
    @Column(name = "timemodified")
    private Long modified;

    public Long getId() {
        return id;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}