package com.diplom.padding.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.*;
import com.diplom.padding.dao.CourseTaskCompetenceMoodleDAO;
import com.diplom.padding.entity.moodle.CourseTaskCompetenceMoodle;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.util.*;

@Repository
public class CourseTaskCompetenceMoodleDAOImpl implements CourseTaskCompetenceMoodleDAO {
    private final EntityManager entityManager;

    @Autowired
    public CourseTaskCompetenceMoodleDAOImpl(@Qualifier("mySQLEntityManager")EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Long> getCompetenceByIdCourseTask(Long id) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<CourseTaskCompetenceMoodle> root = cq.from(CourseTaskCompetenceMoodle.class);
        cq.select(root.get("idCompetence")).where(cb.equal(root.get("idCourseTask"), id));
        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    public List<CourseTaskCompetenceMoodle> findForTheDay() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<CourseTaskCompetenceMoodle> cq = cb.createQuery(CourseTaskCompetenceMoodle.class);
        Root<CourseTaskCompetenceMoodle> root = cq.from(CourseTaskCompetenceMoodle.class);
        Predicate time = cb.lessThan(cb.diff((new Date().getTime() / 1000), root.get("modified")), 88200L);
        cq.where(time);
        return entityManager.createQuery(cq).getResultList();
    }
}