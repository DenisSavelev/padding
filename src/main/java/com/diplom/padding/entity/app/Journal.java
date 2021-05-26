package com.diplom.padding.entity.app;

import javax.persistence.*;
import java.util.List;

@Entity
public class Journal {
    @Id
    private Long id;
    @ManyToOne
    private User user;
    @ManyToOne
    private Task task;
    private String rating;
    @ManyToMany
    List<Competence3> competences;

    public Journal() {
    }

    public Journal(Long id, User user, Task task, String rating, List<Competence3> competences) {
        this.id = id;
        this.user = user;
        this.task = task;
        this.rating = rating;
        this.competences = competences;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public List<Competence3> getCompetences() {
        return competences;
    }

    public void setCompetences(List<Competence3> competences) {
        this.competences = competences;
    }
}