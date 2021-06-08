package com.diplom.padding.model;

import com.diplom.padding.entity.app.Journal;
import java.io.File;
import java.util.List;

public class GitModel {
    private final String userName;
    private final String discipline;
    private final String taskName;
    private final List<File> files;
    private final List<String> origName;
    private final Float score; //5,4,3 оценка 0 не зачтено/1 зачтено

    public GitModel(Journal journal, List<String> origName, List<File> files) {
        this.userName = journal.getUser().getSurname() + journal.getUser().getName();
        this.discipline = "PTEK"; //journal.getTask().getCourse().getShortName();
        this.taskName = journal.getTask().getName();
        this.files = files;
        this.origName = origName;
        this.score = journal.getRating();
    }

    public String getUserName() {
        return userName;
    }

    public String getDiscipline() {
        return discipline;
    }

    public String getTaskName() {
        return taskName;
    }

    public List<File> getFiles() {
        return files;
    }

    public List<String> getOrigName() {
        return origName;
    }

    public Float getScore() {
        return score;
    }
}