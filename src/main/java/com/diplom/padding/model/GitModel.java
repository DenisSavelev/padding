package com.diplom.padding.model;
import com.diplom.padding.entity.app.Journal;

import java.io.File;

public class GitModel {
    private String userName;
    private String discipline;
    private String taskName;
    private File file;
    private String origName;
    private Float score; //5,4,3 оценка 0 не зачтено/1 зачтено

    public GitModel(Journal journal, File file) {
        this.userName = journal.getUser().getSurname() + " " + journal.getUser().getName();
        this.discipline = "IIE"; //journal.getTask().getCourse().getShortName();
        this.taskName = journal.getTask().getName();
        this.file = file;
        this.origName = journal.getFiles().get(0).getTitle();
        this.score = journal.getRating();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getOrigName() {
        return origName;
    }

    public void setOrigName(String origName) {
        this.origName = origName;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }
}