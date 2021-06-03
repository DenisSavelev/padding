package com.diplom.padding.dao;

import com.diplom.padding.entity.app.Task;
import com.diplom.padding.entity.moodle.TaskMoodle;

import java.util.*;

public interface TaskDAO {
    Optional<Task> getById(Long id);
    List<Task> saveAll(List<Task> tasks);
    List<TaskMoodle> findAll();
}