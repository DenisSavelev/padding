package com.diplom.padding.dao;

import com.diplom.padding.entity.app.User;
import com.diplom.padding.entity.moodle.UserMoodle;

import java.util.List;

public interface UserDAO {
    List<User> saveAll(List<User> users);
    List<UserMoodle> findAll();
}