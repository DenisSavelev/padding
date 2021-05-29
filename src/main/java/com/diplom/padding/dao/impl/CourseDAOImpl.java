package com.diplom.padding.dao.impl;

import com.diplom.padding.dao.CourseDAO;
import com.diplom.padding.entity.app.Course;
import com.diplom.padding.entity.moodle.CourseMoodle;
import org.springframework.stereotype.Repository;
import com.diplom.padding.repositories.app.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.diplom.padding.repositories.moodle.CourseMoodleRepository;

import java.util.List;

@Repository
public class CourseDAOImpl implements CourseDAO {
    private final CourseRepository repositoryApp;
    private final CourseMoodleRepository repositoryMoodle;

    @Autowired
    public CourseDAOImpl(CourseRepository repositoryApp, CourseMoodleRepository repositoryMoodle) {
        this.repositoryApp = repositoryApp;
        this.repositoryMoodle = repositoryMoodle;
    }

    @Override
    public List<Course> saveAll(List<Course> courses) {
        return repositoryApp.saveAll(courses);
    }

    @Override
    public List<CourseMoodle> findAll() {
        return repositoryMoodle.findAll();
    }
}