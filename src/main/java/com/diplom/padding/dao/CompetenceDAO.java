package com.diplom.padding.dao;

import com.diplom.padding.entity.app.Competence;
import com.diplom.padding.entity.moodle.CompetenceMoodle;

import java.util.*;

public interface CompetenceDAO {
    Optional<Competence> getById(Long id);
    List<Competence> saveAll(List<Competence> competences);
    List<CompetenceMoodle> findAll();
    List<CompetenceMoodle> findForTheDay();
}