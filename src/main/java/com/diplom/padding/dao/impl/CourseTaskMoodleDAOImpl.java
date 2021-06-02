package com.diplom.padding.dao.impl;

import org.springframework.stereotype.Repository;
import com.diplom.padding.dao.CourseTaskMoodleDAO;
import org.springframework.beans.factory.annotation.*;
import com.diplom.padding.entity.moodle.CourseTaskMoodle;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.util.List;

@Repository
public class CourseTaskMoodleDAOImpl implements CourseTaskMoodleDAO {
    private final EntityManager em;

    @Autowired
    public CourseTaskMoodleDAOImpl(@Qualifier("mySQLEntityManager")EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Long> getIdByIdTaskAndIdCourse(Long idTask, Long idCourse) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<CourseTaskMoodle> root = cq.from(CourseTaskMoodle.class);
        Predicate predicate = cb.conjunction();
        Predicate area = cb.and(cb.equal(root.get("idTask"), idTask), cb.equal(root.get("idCourse"), idCourse));
        predicate = cb.and(predicate, area);
        Predicate area1 = cb.or(cb.equal(root.get("module"), 1L), cb.equal(root.get("module"), 17L));
        predicate = cb.and(predicate, area1);
        cq.select(root.get("id")).where(predicate);
        TypedQuery<Long> tq = em.createQuery(cq);
        return tq.getResultList();
    }
}