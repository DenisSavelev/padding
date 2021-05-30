package com.diplom.padding.model;

public class GitModel {
    private String userName;
    private String discipline;
    private String taskName;

    public GitModel() {
    }

    public GitModel(String userName, String discipline, String taskName) {
        this.userName = userName;
        this.discipline = discipline;
        this.taskName = taskName;
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
