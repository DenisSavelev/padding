package com.diplom.padding.dao.impl;

import com.diplom.padding.entity.app.*;
import com.diplom.padding.repositories.app.*;
import com.diplom.padding.dao.Competence2DAO;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.*;
import com.diplom.padding.entity.moodle.CompetenceMoodle2;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.util.*;

@Repository
public class Competence2DAOImpl implements Competence2DAO {
    private final EntityManager em;
    private final Competence2Repository repositoryApp;
    private final Competence3Repository repositoryApp2;

    @Autowired
    public Competence2DAOImpl(@Qualifier("mySQLEntityManager") EntityManager em, Competence2Repository repositoryApp,
                              Competence3Repository repositoryApp2) {
        this.em = em;
        this.repositoryApp = repositoryApp;
        this.repositoryApp2 = repositoryApp2;
    }

    @Override
    public Optional<Competence2> getCompetence2ById(Long id) {
        return repositoryApp.findById(id);
    }

    @Override
    public List<Competence2> saveAllCompetence2(List<Competence2> competences) {
        return repositoryApp.saveAll(competences);
    }

    @Override
    public List<Competence3> saveAllCompetence3(List<Competence3> competences) {
        return repositoryApp2.saveAll(competences);
    }

    @Override
    public List<CompetenceMoodle2> findAllCompetence2() {
        return getCompetenceByLevel(true);
    }

    @Override
    public List<CompetenceMoodle2> findAllCompetence3() {
        return getCompetenceByLevel(false);
    }

    private List<CompetenceMoodle2> getCompetenceByLevel(boolean isTwo) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<CompetenceMoodle2> cq = cb.createQuery(CompetenceMoodle2.class);
        Root<CompetenceMoodle2> root = cq.from(CompetenceMoodle2.class);
        if (isTwo) {
            cq.where(cb.equal(root.get("idParent"), 0));
        } else {
            cq.where(cb.notEqual(root.get("idParent"), 0));
        }
        TypedQuery<CompetenceMoodle2> tq = em.createQuery(cq);
        return tq.getResultList();
    }
}