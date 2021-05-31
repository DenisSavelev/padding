package com.diplom.padding.dao.impl;

import com.diplom.padding.dao.RoleDAO;
import com.diplom.padding.entity.app.Role;
import org.springframework.stereotype.Repository;
import com.diplom.padding.entity.moodle.RoleMoodle;
import com.diplom.padding.repositories.app.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.diplom.padding.repositories.moodle.RoleMoodleRepository;

import java.util.List;

@Repository
public class RoleDAOImpl implements RoleDAO {
    private final RoleMoodleRepository repositoryMoodle;
    private final RoleRepository repositoryApp;

    @Autowired
    public RoleDAOImpl(RoleMoodleRepository repositoryMoodle, RoleRepository repositoryApp) {
        this.repositoryMoodle = repositoryMoodle;
        this.repositoryApp = repositoryApp;
    }

    @Override
    public List<Role> saveAll(List<Role> roles) {
        return repositoryApp.saveAll(roles);
    }

    @Override
    public List<RoleMoodle> findAll() {
        return repositoryMoodle.findAll();
    }
}