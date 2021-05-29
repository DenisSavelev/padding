package com.diplom.padding.dao;

import com.diplom.padding.entity.app.Course;
import com.diplom.padding.entity.moodle.CourseMoodle;

import java.util.List;

public interface CourseDAO {
    List<Course> saveAll(List<Course> courses);
    List<CourseMoodle> findAll();
}