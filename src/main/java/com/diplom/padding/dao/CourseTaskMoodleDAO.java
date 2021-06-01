package com.diplom.padding.dao;

import com.diplom.padding.entity.moodle.CourseTaskMoodle;

public interface CourseTaskMoodleDAO {
    CourseTaskMoodle getByIdTaskAndIdCourse(Long idTask, Long idCourse);
}