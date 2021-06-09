package com.diplom.padding.dao.impl;

import com.diplom.padding.dao.JournalDAO;
import com.diplom.padding.entity.app.Journal;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.*;
import com.diplom.padding.entity.moodle.*;
import com.diplom.padding.repositories.app.JournalRepository;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.util.*;

@Repository
public class JournalDAOImpl implements JournalDAO {
    private final EntityManager entityManager;
    private final JournalRepository repositoryApp;

    @Autowired
    public JournalDAOImpl(@Qualifier("mySQLEntityManager")EntityManager entityManager, JournalRepository repositoryApp) {
        this.entityManager = entityManager;
        this.repositoryApp = repositoryApp;
    }

    @Override
    public List<Journal> saveAll(Collection<Journal> journals) {
        return repositoryApp.saveAll(journals);
    }

    @Override
    public Optional<Journal> getById(Long id) {
        return repositoryApp.findById(id);
    }

    @Override
    public JournalMoodle getByUserAndFile(Long idUser, Long idFile) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<JournalMoodle> cq = cb.createQuery(JournalMoodle.class);
        Root<JournalMoodle> root = cq.from(JournalMoodle.class);
        Root<TaskMoodle> task = cb.createQuery(TaskMoodle.class).from(TaskMoodle.class);
        //Join<JournalMoodle, TaskMoodle> join = root.join("idTask").on(cb.equal(root.get("idTask"), task.get("id")));
        cq.where(cb.equal(root.get("user"), idUser));
        return entityManager.createQuery(cq).getSingleResult();
    }

    @Override
    public List<JournalMoodle> findAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<JournalMoodle> cq = cb.createQuery(JournalMoodle.class);
        Root<JournalMoodle> root = cq.from(JournalMoodle.class);
        cq.where(cb.isNotNull(root.get("grade")));
        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    public List<JournalMoodle> findForTheDay() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<JournalMoodle> cq = cb.createQuery(JournalMoodle.class);
        Root<JournalMoodle> root = cq.from(JournalMoodle.class);
        Predicate time = cb.lessThan(cb.diff((new Date().getTime() / 1000), root.get("modified")), 88200L);
        cq.where(cb.and(time, cb.isNotNull(root.get("grade"))));
        return entityManager.createQuery(cq).getResultList();
    }
}