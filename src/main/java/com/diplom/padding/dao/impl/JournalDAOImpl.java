package com.diplom.padding.dao.impl;

import com.diplom.padding.entity.app.*;
import com.diplom.padding.dao.JournalDAO;
import com.diplom.padding.entity.moodle.*;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.*;
import com.diplom.padding.repositories.app.JournalRepository;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.util.*;

@Repository
public class JournalDAOImpl implements JournalDAO {
    private final EntityManager manager;
    private final EntityManager entityManager;
    private final JournalRepository repositoryApp;

    @Autowired
    public JournalDAOImpl(@Qualifier("postgreEntityManager")EntityManager manager,
                          @Qualifier("mySQLEntityManager")EntityManager entityManager, JournalRepository repositoryApp) {
        this.manager = manager;
        this.entityManager = entityManager;
        this.repositoryApp = repositoryApp;
    }

    @Override
    public Journal save(Journal journal) {
        return repositoryApp.save(journal);
    }

    @Override
    public List<Journal> saveAll(Collection<Journal> journals) {
        return repositoryApp.saveAll(journals);
    }

    @Override
    public List<Journal> getByFile(Long idFile) {
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<Journal> cq = cb.createQuery(Journal.class);
        Root<Journal> root = cq.from(Journal.class);
        Join<Journal, File> join = root.join("files");
        cq.where(join.get("id").in(idFile));
        return manager.createQuery(cq).getResultList();
    }

    @Override
    public Optional<Journal> getById(Long id) {
        return repositoryApp.findById(id);
    }

    @Override
    public Journal getByItemFile(Long itemFile) {
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<Journal> cq = cb.createQuery(Journal.class);
        Root<Journal> root = cq.from(Journal.class);
        Join<Journal, File> join = root.join("files");
        cq.where(join.get("item").in(itemFile));
        return manager.createQuery(cq).getSingleResult();
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