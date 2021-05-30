package com.diplom.padding.model;

public class GitModel {
    private String userName;
    private String discipline;
    private String taskName;
    private int score; //5,4,3 оценка 0 не зачтено/1 зачтено

    public GitModel() {
    }

    public GitModel(String userName, String discipline, String taskName, int score) {
        this.userName = userName;
        this.discipline = discipline;
        this.taskName = taskName;
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
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
}
