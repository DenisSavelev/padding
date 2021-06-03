package com.diplom.padding.dao;

import com.diplom.padding.entity.app.User;
import com.diplom.padding.entity.moodle.UserMoodle;

import java.util.*;

public interface UserDAO {
    Optional<User> getById(Long id);
    List<User> saveAll(List<User> users);
    List<UserMoodle> findAll();
    List<UserMoodle> findForTheDay();
}