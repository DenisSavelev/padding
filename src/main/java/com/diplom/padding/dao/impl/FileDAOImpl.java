package com.diplom.padding.dao.impl;

import com.diplom.padding.dao.FileDAO;
import com.diplom.padding.entity.app.File;
import org.springframework.stereotype.Repository;
import com.diplom.padding.entity.moodle.FileMoodle;
import org.springframework.beans.factory.annotation.*;
import com.diplom.padding.repositories.app.FileRepository;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.util.List;

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
    public List<File> getByUserAndTask(Long idUser, Long idTask) {
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<File> cq = cb.createQuery(File.class);
        Root<File> root = cq.from(File.class);
        cq.where(root.get("id").in(getIdByUserAndTask(idUser, idTask)));
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
    public List<Long> getIdByUserAndTask(Long idUser, Long idTask) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<FileMoodle> root = cq.from(FileMoodle.class);
        Predicate predicate = cb.conjunction();
        Predicate id = cb.and(cb.equal(root.get("idUser"), idUser), cb.equal(root.get("idTask"), idTask));
        predicate = cb.and(predicate, id);
        cq.select(root.get("id")).where(cb.and(filter(cb, root), predicate));
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
}