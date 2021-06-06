package com.diplom.padding.dao;

import com.diplom.padding.entity.app.Journal;
import com.diplom.padding.entity.moodle.JournalMoodle;

import java.util.List;

public interface JournalDAO {
    List<Journal> saveAll(List<Journal> journals);
    List<Journal> findAllJournal();
    List<JournalMoodle> findAll();
    List<JournalMoodle> findForTheDay();
}