package com.diplom.padding.dao.impl;

import com.diplom.padding.dao.FileDAO;
import com.diplom.padding.entity.app.File;
import org.springframework.stereotype.Repository;
import com.diplom.padding.entity.moodle.FileMoodle;
import org.springframework.beans.factory.annotation.*;
import com.diplom.padding.repositories.app.FileRepository;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class FileDAOImpl implements FileDAO {
    private final FileRepository repositoryApp;
    private final EntityManager entityManager;

    @Autowired
    public FileDAOImpl(FileRepository repositoryApp, @Qualifier("mySQLEntityManager")EntityManager entityManager) {
        this.repositoryApp = repositoryApp;
        this.entityManager = entityManager;
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
        cq.where(cb.greaterThan(root.get("idUser"), 2L));
        return entityManager.createQuery(cq).getResultList();
    }
}