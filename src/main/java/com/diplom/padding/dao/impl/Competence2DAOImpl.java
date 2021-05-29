package com.diplom.padding.dao.impl;

import com.diplom.padding.dao.Competence2DAO;
import org.springframework.stereotype.Repository;
import com.diplom.padding.entity.app.Competence2;
import com.diplom.padding.entity.moodle.CompetenceMoodle2;
import org.springframework.beans.factory.annotation.Autowired;
import com.diplom.padding.repositories.app.Competence2Repository;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.util.List;

@Repository
public class Competence2DAOImpl implements Competence2DAO {
    private final Competence2Repository repositoryApp;
    private final LocalContainerEntityManagerFactoryBean lem;

    @Autowired
    public Competence2DAOImpl(Competence2Repository repositoryApp, LocalContainerEntityManagerFactoryBean lem) {
        this.repositoryApp = repositoryApp;
        this.lem = lem;
    }

    @Override
    public List<Competence2> saveAll(List<Competence2> competences) {
        return repositoryApp.saveAll(competences);
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
        EntityManager em = lem.createNativeEntityManager(lem.getJpaPropertyMap());
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