package com.diplom.padding.dao.impl;

import com.diplom.padding.dao.JournalDAO;
import com.diplom.padding.entity.app.Journal;
import org.springframework.stereotype.Repository;
import com.diplom.padding.entity.moodle.JournalMoodle;
import com.diplom.padding.repositories.app.JournalRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.util.List;

@Repository
public class JournalDAOImpl implements JournalDAO {
    private final EntityManager entityManager;
    private final JournalRepository repositoryApp;

    @Autowired
    public JournalDAOImpl(EntityManager entityManager, JournalRepository repositoryApp) {
        this.entityManager = entityManager;
        this.repositoryApp = repositoryApp;
    }

    @Override
    public List<Journal> saveAll(List<Journal> journals) {
        return repositoryApp.saveAll(journals);
    }

    @Override
    public List<JournalMoodle> findAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<JournalMoodle> cq = cb.createQuery(JournalMoodle.class);
        Root<JournalMoodle> root = cq.from(JournalMoodle.class);
        cq.where(cb.isNotNull(root.get("grade")));
        return entityManager.createQuery(cq).getResultList();
    }
}