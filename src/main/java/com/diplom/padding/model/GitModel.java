package com.diplom.padding.model;

import com.diplom.padding.entity.app.Journal;
import java.io.File;

public class GitModel {
    private final String userName;
    private final String discipline;
    private final String taskName;
    private final File file;
    private final String origName;
    private final Float score; //5,4,3 оценка 0 не зачтено/1 зачтено

    public GitModel(Journal journal, File file) {
        this.userName = journal.getUser().getSurname() + journal.getUser().getName();
        this.discipline = "PTEK"; //journal.getTask().getCourse().getShortName();
        this.taskName = journal.getTask().getName();
        this.file = file;
        this.origName = journal.getFiles().get(0).getTitle();
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

    public File getFile() {
        return file;
    }

    public String getOrigName() {
        return origName;
    }

    public Float getScore() {
        return score;
    }
}