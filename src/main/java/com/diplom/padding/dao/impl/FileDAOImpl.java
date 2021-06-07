package com.diplom.padding.dao.impl;

import com.diplom.padding.dao.FileDAO;
import com.diplom.padding.entity.app.File;
import org.springframework.stereotype.Repository;
import com.diplom.padding.entity.moodle.FileMoodle;
import org.springframework.beans.factory.annotation.*;
import com.diplom.padding.repositories.app.FileRepository;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.util.*;

@Repository
public class FileDAOImpl implements FileDAO {
    private final EntityManager manager;
    private final FileRepository repositoryApp;
    private final EntityManager entityManager;

    @Autowired
    public FileDAOImpl(FileRepository repositoryApp, @Qualifier("mySQLEntityManager")EntityManager entityManager,
                       @Qualifier("postgreEntityManager")EntityManager manager) {
        this.manager = manager;
        this.repositoryApp = repositoryApp;
        this.entityManager = entityManager;
    }

    @Override
    public List<File> getByItemAndUser(List<Long> idItem, Long idUser) {
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<File> cq = cb.createQuery(File.class);
        Root<File> root = cq.from(File.class);
        cq.where(root.get("id").in(getIdFiles(idItem, idUser)));
        return manager.createQuery(cq).getResultList();
    }

    @Override
    public List<File> saveAll(List<File> files) {
        return repositoryApp.saveAll(files);
    }

    @Override
    public List<FileMoodle> findAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<FileMoodle> cq = cb.createQuery(FileMoodle.class);
        Root<FileMoodle> root = cq.from(FileMoodle.class);
        cq.where(filter(cb, root));
        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    public List<FileMoodle> findForTheDay() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<FileMoodle> cq = cb.createQuery(FileMoodle.class);
        Root<FileMoodle> root = cq.from(FileMoodle.class);
        Predicate time = cb.lessThan(cb.diff((new Date().getTime() / 1000), root.get("modified")), 88200L);
        cq.where(cb.and(filter(cb, root), time));
        return entityManager.createQuery(cq).getResultList();
    }

    private Predicate filter(CriteriaBuilder cb, Root<FileMoodle> root) {
        Predicate predicate = cb.conjunction();
        Predicate id = cb.greaterThan(root.get("idUser"), 2L);
        predicate = cb.and(predicate, id);
        Predicate notNull = cb.and(cb.isNotNull(root.get("idUser")), cb.isNotNull(root.get("author")));
        predicate = cb.and(predicate, notNull);
        Predicate component = cb.notLike(root.get("component"), "user");
        return cb.and(predicate, component);
    }

    private List<Long> getIdFiles(List<Long> idItem, Long idUser) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<FileMoodle> root = cq.from(FileMoodle.class);
        Predicate predicate = cb.conjunction();
        Predicate id = cb.equal(root.get("idUser"), idUser);
        predicate = cb.and(predicate, id);
        Predicate area = root.get("idItem").in(idItem);
        predicate = cb.and(predicate, area);
        cq.select(root.get("id")).where(cb.and(filter(cb, root), predicate));
        return entityManager.createQuery(cq).getResultList();
    }
}