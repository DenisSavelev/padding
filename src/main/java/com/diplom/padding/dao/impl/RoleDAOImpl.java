package com.diplom.padding.dao.impl;

import com.diplom.padding.dao.RoleDAO;
import com.diplom.padding.entity.app.Role;
import org.springframework.stereotype.Repository;
import com.diplom.padding.repositories.app.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Repository
public class RoleDAOImpl implements RoleDAO {
    private final RoleRepository repository;

    @Autowired
    public RoleDAOImpl(RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Role> saveAll(List<Role> roles) {
        return repository.saveAll(roles);
    }
}