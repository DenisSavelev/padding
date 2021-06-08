package com.diplom.padding.dao.impl;

import com.diplom.padding.dao.TaskDAO;
import com.diplom.padding.entity.moodle.*;
import com.diplom.padding.entity.app.Task;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.*;
import com.diplom.padding.repositories.app.TaskRepository;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.util.*;

@Repository
public class TaskDAOImpl implements TaskDAO {
    private final TaskRepository repositoryApp;
    private final EntityManager entityManager;

    @Autowired
    public TaskDAOImpl(TaskRepository repositoryApp, @Qualifier("mySQLEntityManager")EntityManager entityManager) {
        this.repositoryApp = repositoryApp;
        this.entityManager = entityManager;
    }

    @Override
    public Optional<Task> getById(Long id) {
        return repositoryApp.findById(id);
    }

    @Override
    public List<Task> saveAll(List<Task> tasks) {
        return repositoryApp.saveAll(tasks);
    }

    @Override
    public List<TaskMoodle> findAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<TaskMoodle> cq = cb.createQuery(TaskMoodle.class);
        Root<TaskMoodle> root = cq.from(TaskMoodle.class);
        cq.where(filter(cb, root));
        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    public List<TaskMoodle> findForTheDay() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<TaskMoodle> cq = cb.createQuery(TaskMoodle.class);
        Root<TaskMoodle> root = cq.from(TaskMoodle.class);
        Predicate time = cb.lessThan(cb.diff((new Date().getTime() / 1000), root.get("modified")), 88200L);
        cq.where(cb.and(filter(cb, root), time));
        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    public List<Long> getIdFilesByIdTask(Long idTask) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<FileTaskMoodle> root = cq.from(FileTaskMoodle.class);
        cq.select(root.get("idFile")).where(cb.equal(root.get("idTask"), getIdItem(cb, idTask)));
        return entityManager.createQuery(cq).getResultList();
    }

    private Long getIdItem(CriteriaBuilder cb, Long idTask) {
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<TaskMoodle> root = cq.from(TaskMoodle.class);
        cq.select(root.get("idItem")).where(cb.equal(root.get("id"), idTask));
        return entityManager.createQuery(cq).getSingleResult();
    }

    private Predicate filter(CriteriaBuilder cb, Root<TaskMoodle> root) {
        Predicate predicate = cb.conjunction();
        Predicate filter = cb.notEqual(root.get("id"), 1L);
        return cb.and(predicate, filter);
    }
}