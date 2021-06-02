package com.diplom.padding.dao;

import java.util.List;

public interface CourseTaskMoodleDAO {
    List<Long> getIdByIdTaskAndIdCourse(Long idTask, Long idCourse);
}