package com.diplom.padding.dao;

import com.diplom.padding.entity.app.*;
import com.diplom.padding.entity.moodle.CompetenceMoodle2;

import java.util.*;

public interface Competence2DAO {
    Optional<Competence2> getCompetence2ById(Long id);
    Optional<Competence3> getCompetence3ById(Long id);
    List<Competence2> saveAllCompetence2(List<Competence2> competences);
    List<Competence3> saveAllCompetence3(List<Competence3> competences);
    List<CompetenceMoodle2> findAllCompetence2();
    List<CompetenceMoodle2> findAllCompetence3();
}