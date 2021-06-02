package com.diplom.padding.dao;

import com.diplom.padding.entity.app.Role;
import com.diplom.padding.entity.moodle.RoleMoodle;

import java.util.List;

public interface RoleDAO {
    List<Role> saveAll(List<Role> roles);
    List<RoleMoodle> findAll();
}