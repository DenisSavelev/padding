package com.diplom.padding.dao.impl;

import com.diplom.padding.dao.TaskDAO;
import com.diplom.padding.entity.app.Task;
import org.springframework.stereotype.Repository;
import com.diplom.padding.entity.moodle.TaskMoodle;
import com.diplom.padding.repositories.app.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.diplom.padding.repositories.moodle.TaskMoodleRepository;

import java.util.List;

@Repository
public class TaskDAOImpl implements TaskDAO {
    private final TaskRepository repositoryApp;
    private final TaskMoodleRepository repositoryMoodle;

    @Autowired
    public TaskDAOImpl(TaskRepository repositoryApp, TaskMoodleRepository repositoryMoodle) {
        this.repositoryApp = repositoryApp;
        this.repositoryMoodle = repositoryMoodle;
    }

    @Override
    public List<Task> saveAll(List<Task> tasks) {
        return repositoryApp.saveAll(tasks);
    }

    @Override
    public List<TaskMoodle> findAll() {
        return repositoryMoodle.findAll();
    }
}