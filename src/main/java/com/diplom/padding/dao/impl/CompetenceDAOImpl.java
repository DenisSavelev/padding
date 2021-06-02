package com.diplom.padding.dao.impl;

import com.diplom.padding.dao.CompetenceDAO;
import com.diplom.padding.entity.app.Competence;
import org.springframework.stereotype.Repository;
import com.diplom.padding.entity.moodle.CompetenceMoodle;
import org.springframework.beans.factory.annotation.Autowired;
import com.diplom.padding.repositories.app.CompetenceRepository;
import com.diplom.padding.repositories.moodle.CompetenceMoodleRepository;

import java.util.*;

@Repository
public class CompetenceDAOImpl implements CompetenceDAO {
    private final CompetenceRepository repositoryApp;
    private final CompetenceMoodleRepository repositoryMoodle;

    @Autowired
    public CompetenceDAOImpl(CompetenceRepository repositoryApp, CompetenceMoodleRepository repositoryMoodle) {
        this.repositoryApp = repositoryApp;
        this.repositoryMoodle = repositoryMoodle;
    }

    @Override
    public Optional<Competence> getById(Long id) {
        return repositoryApp.findById(id);
    }

    @Override
    public List<Competence> saveAll(List<Competence> competences) {
        return repositoryApp.saveAll(competences);
    }

    @Override
    public List<CompetenceMoodle> findAll() {
        return repositoryMoodle.findAll();
    }
}