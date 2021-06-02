package com.diplom.padding.dao.impl;

import com.diplom.padding.dao.UserDAO;
import com.diplom.padding.entity.app.User;
import org.springframework.stereotype.Repository;
import com.diplom.padding.entity.moodle.UserMoodle;
import org.springframework.beans.factory.annotation.*;
import com.diplom.padding.repositories.app.UserRepository;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.util.*;

@Repository
public class UserDAOImpl implements UserDAO {
    private final UserRepository repositoryApp;
    private final EntityManager entityManager;

    @Autowired
    public UserDAOImpl(UserRepository repositoryApp, @Qualifier("mySQLEntityManager")EntityManager entityManager) {
        this.repositoryApp = repositoryApp;
        this.entityManager = entityManager;
    }

    @Override
    public Optional<User> getById(Long id) {
        return repositoryApp.findById(id);
    }

    @Override
    public List<User> saveAll(List<User> users) {
        return repositoryApp.saveAll(users);
    }

    @Override
    public List<UserMoodle> findAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<UserMoodle> cq = cb.createQuery(UserMoodle.class);
        Root<UserMoodle> root = cq.from(UserMoodle.class);
        cq.where(cb.and(cb.greaterThan(root.get("id"), 2L), cb.isNotNull(root.get("id"))));
        return entityManager.createQuery(cq).getResultList();
    }
}