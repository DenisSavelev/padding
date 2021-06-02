package com.diplom.padding.dao;

import com.diplom.padding.entity.moodle.*;
import com.diplom.padding.entity.app.Journal;

import java.util.List;

public interface JournalDAO {
    List<Journal> saveAll(List<Journal> journals);
    List<AssignJournalMoodle> findAllAssign();
    List<QuizJournalMoodle> findAllQuiz();
}