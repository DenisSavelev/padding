package com.diplom.padding.dao;

import com.diplom.padding.entity.moodle.TaskMoodle;

import java.util.List;

public interface TaskDAO {
    List<TaskMoodle> findAll();
}