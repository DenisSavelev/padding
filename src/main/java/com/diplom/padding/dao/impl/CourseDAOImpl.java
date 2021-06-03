package com.diplom.padding.dao.impl;

import com.diplom.padding.dao.CourseDAO;
import com.diplom.padding.entity.app.Course;
import org.springframework.stereotype.Repository;
import com.diplom.padding.entity.moodle.CourseMoodle;
import org.springframework.beans.factory.annotation.*;
import com.diplom.padding.repositories.app.CourseRepository;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.util.*;

@Repository
public class CourseDAOImpl implements CourseDAO {
    private final CourseRepository repositoryApp;
    private final EntityManager entityManager;

    @Autowired
    public CourseDAOImpl(CourseRepository repositoryApp, @Qualifier("mySQLEntityManager") EntityManager entityManager) {
        this.repositoryApp = repositoryApp;
        this.entityManager = entityManager;
    }

    @Override
    public Optional<Course> getById(Long id) {
        return repositoryApp.findById(id);
    }

    @Override
    public List<Course> saveAll(List<Course> courses) {
        return repositoryApp.saveAll(courses);
    }

    @Override
    public List<CourseMoodle> findAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<CourseMoodle> cq = cb.createQuery(CourseMoodle.class);
        Root<CourseMoodle> root = cq.from(CourseMoodle.class);
        cq.where(cb.greaterThan(root.get("id"), 1L));
        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    public List<CourseMoodle> findForTheDay() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<CourseMoodle> cq = cb.createQuery(CourseMoodle.class);
        Root<CourseMoodle> root = cq.from(CourseMoodle.class);
        Predicate time = cb.lessThan(cb.diff((new Date().getTime() / 1000), root.get("modified")), 88200L);
        cq.where(cb.and(cb.greaterThan(root.get("id"), 1L), time));
        return entityManager.createQuery(cq).getResultList();
    }
}