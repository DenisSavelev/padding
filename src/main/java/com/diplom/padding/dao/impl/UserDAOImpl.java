package com.diplom.padding.dao.impl;

import com.diplom.padding.dao.UserDAO;
import com.diplom.padding.entity.app.User;
import org.springframework.stereotype.Repository;
import com.diplom.padding.entity.moodle.UserMoodle;
import com.diplom.padding.repositories.app.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.diplom.padding.repositories.moodle.UserMoodleRepository;

import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {
    private final UserRepository repositoryApp;
    private final UserMoodleRepository repositoryMoodle;

    @Autowired
    public UserDAOImpl(UserRepository repositoryApp, UserMoodleRepository repositoryMoodle) {
        this.repositoryApp = repositoryApp;
        this.repositoryMoodle = repositoryMoodle;
    }

    @Override
    public List<User> saveAll(List<User> users) {
        return repositoryApp.saveAll(users);
    }

    @Override
    public List<UserMoodle> findAll() {
        return repositoryMoodle.findAll();
    }
}