package com.diplom.padding.dao;

import com.diplom.padding.entity.app.Journal;
import com.diplom.padding.entity.moodle.JournalMoodle;

import java.util.*;

public interface JournalDAO {
    List<Journal> saveAll(List<Journal> journals);
    Optional<Journal> getById(Long id);
    List<JournalMoodle> findAll();
    List<JournalMoodle> findForTheDay();
}