package com.diplom.padding.dao.impl;

import com.diplom.padding.dao.JournalDAO;
import com.diplom.padding.entity.moodle.*;
import com.diplom.padding.entity.app.Journal;
import com.diplom.padding.repositories.moodle.*;
import org.springframework.stereotype.Repository;
import com.diplom.padding.repositories.app.JournalRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Repository
public class JournalDAOImpl implements JournalDAO {
    private final JournalRepository repositoryApp;
    private final AssignJournalMoodleRepository repositoryAssign;
    private final QuizJournalMoodleRepository repositoryQuiz;

    @Autowired
    public JournalDAOImpl(JournalRepository repositoryApp, AssignJournalMoodleRepository repositoryAssign,
                          QuizJournalMoodleRepository repositoryQuiz) {
        this.repositoryApp = repositoryApp;
        this.repositoryAssign = repositoryAssign;
        this.repositoryQuiz = repositoryQuiz;
    }

    @Override
    public List<Journal> saveAll(List<Journal> journals) {
        return repositoryApp.saveAll(journals);
    }

    @Override
    public List<AssignJournalMoodle> findAllAssign() {
        return repositoryAssign.findAll();
    }

    @Override
    public List<QuizJournalMoodle> findAllQuiz() {
        return repositoryQuiz.findAll();
    }
}