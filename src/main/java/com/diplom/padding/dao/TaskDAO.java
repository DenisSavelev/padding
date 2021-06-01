package com.diplom.padding.dao;

import com.diplom.padding.entity.app.Task;
import com.diplom.padding.entity.moodle.TaskMoodle;

import java.util.List;

public interface TaskDAO {
    List<Task> saveAll(List<Task> tasks);
    List<TaskMoodle> findAll();
}