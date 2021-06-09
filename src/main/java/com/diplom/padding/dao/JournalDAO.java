package com.diplom.padding.dao;

import com.diplom.padding.entity.app.Journal;
import com.diplom.padding.entity.moodle.JournalMoodle;

import java.util.*;

public interface JournalDAO {
    List<Journal> saveAll(Collection<Journal> journals);
    Optional<Journal> getById(Long id);
    JournalMoodle getByUserAndFile(Long idUser, Long idFile);
    List<JournalMoodle> findAll();
    List<JournalMoodle> findForTheDay();
}