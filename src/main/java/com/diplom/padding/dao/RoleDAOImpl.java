package com.diplom.padding.dao;

import org.springframework.stereotype.Repository;
import com.diplom.padding.entity.moodle.RoleMoodle;
import org.springframework.beans.factory.annotation.Autowired;
import com.diplom.padding.repositories.moodle.RoleMoodleRepository;

import java.util.List;

@Repository
public class RoleDAOImpl implements RoleDAO{
    private final RoleMoodleRepository repository;

    @Autowired
    public RoleDAOImpl(RoleMoodleRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<RoleMoodle> findAll() {
        return repository.findAll();
    }
}