package com.diplom.padding.dao;

import com.diplom.padding.entity.app.Competence2;
import com.diplom.padding.entity.moodle.CompetenceMoodle2;

import java.util.List;

public interface Competence2DAO {
    List<Competence2> saveAll(List<Competence2> competences);
    List<CompetenceMoodle2> findAllCompetence2();
    List<CompetenceMoodle2> findAllCompetence3();
}