package com.diplom.padding.dao.impl;

import com.diplom.padding.dao.CompetenceDAO;
import com.diplom.padding.entity.app.Competence;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import com.diplom.padding.entity.moodle.CompetenceMoodle;
import org.springframework.beans.factory.annotation.Autowired;
import com.diplom.padding.repositories.app.CompetenceRepository;
import com.diplom.padding.repositories.moodle.CompetenceMoodleRepository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

@Repository
public class CompetenceDAOImpl implements CompetenceDAO {
    private final CompetenceRepository repositoryApp;
    private final CompetenceMoodleRepository repositoryMoodle;
    private final EntityManager entityManager;

    @Autowired
    public CompetenceDAOImpl(CompetenceRepository repositoryApp, CompetenceMoodleRepository repositoryMoodle, @Qualifier("mySQLEntityManager") EntityManager entityManager) {
        this.repositoryApp = repositoryApp;
        this.repositoryMoodle = repositoryMoodle;
        this.entityManager = entityManager;
    }

    @Override
    public Optional<Competence> getById(Long id) {
        return repositoryApp.findById(id);
    }

    @Override
    public List<Competence> saveAll(List<Competence> competences) {
        return repositoryApp.saveAll(competences);
    }

    @Override
    public List<CompetenceMoodle> findAll() {
        return repositoryMoodle.findAll();
    }

    @Override
    public List<CompetenceMoodle> findForTheDay() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<CompetenceMoodle> cq = cb.createQuery(CompetenceMoodle.class);
        Root<CompetenceMoodle> root = cq.from(CompetenceMoodle.class);
        Predicate time = cb.lessThan(cb.diff((new Date().getTime() / 1000), root.get("modified")), 88200L);
        cq.where(time);
        return entityManager.createQuery(cq).getResultList();
    }
}