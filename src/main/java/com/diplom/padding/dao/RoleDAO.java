package com.diplom.padding.dao;

import com.diplom.padding.entity.moodle.RoleMoodle;

import java.util.List;

public interface RoleDAO {
    List<RoleMoodle> findAll();
}