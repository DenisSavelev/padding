package com.diplom.padding.dao;

import com.diplom.padding.entity.moodle.CourseTaskCompetenceMoodle;

import java.util.List;

public interface CourseTaskCompetenceMoodleDAO {
    List<Long> getCompetenceByIdCourseTask(Long id);
    List<CourseTaskCompetenceMoodle> findForTheDay();
}