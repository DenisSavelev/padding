package com.diplom.padding.dao;

import com.diplom.padding.entity.app.Course;
import com.diplom.padding.entity.moodle.CourseMoodle;

import java.util.*;

public interface CourseDAO {
    Optional<Course> getById(Long id);
    List<Course> saveAll(List<Course> courses);
    List<CourseMoodle> findAll();
    List<CourseMoodle> findForTheDay();
}