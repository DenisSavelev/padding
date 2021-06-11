package com.diplom.padding.dao;

import com.diplom.padding.entity.app.Journal;
import com.diplom.padding.entity.moodle.JournalMoodle;

import java.util.*;

public interface JournalDAO {
    Journal save(Journal journal);
    List<Journal> saveAll(Collection<Journal> journals);
    List<Journal> getByFile(Long idFile);
    Optional<Journal> getById(Long id);
    Journal getByItemFile(Long itemFile);
    List<JournalMoodle> findAll();
    List<JournalMoodle> findForTheDay();
}