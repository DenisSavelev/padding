package com.diplom.padding.dao.impl;

import org.springframework.stereotype.Repository;
import com.diplom.padding.dao.CourseTaskMoodleDAO;
import org.springframework.beans.factory.annotation.*;
import com.diplom.padding.entity.moodle.CourseTaskMoodle;

import javax.persistence.*;
import javax.persistence.criteria.*;

@Repository
public class CourseTaskMoodleDAOImpl implements CourseTaskMoodleDAO {
    private final EntityManager em;

    @Autowired
    public CourseTaskMoodleDAOImpl(@Qualifier("mySQLEntityManager")EntityManager em) {
        this.em = em;
    }

    @Override
    public CourseTaskMoodle getByIdTaskAndIdCourse(Long idTask, Long idCourse) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<CourseTaskMoodle> cq = cb.createQuery(CourseTaskMoodle.class);
        Root<CourseTaskMoodle> root = cq.from(CourseTaskMoodle.class);
        cq.where(cb.and(cb.equal(root.get("idTask"), idTask), cb.equal(root.get("idCourse"), idCourse)));
        TypedQuery<CourseTaskMoodle> tq = em.createQuery(cq);
        return tq.getSingleResult();
    }
}