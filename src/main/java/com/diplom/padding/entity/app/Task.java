package com.diplom.padding.entity.app;

import javax.persistence.*;
import java.util.List;

@Entity
public class Task {
    @Id
    private Long id;
    private String maxRating;
    private String minRating;
    @ManyToOne
    private Level level;
    @ManyToOne
    private Course course;
    @ManyToMany
    List<Competence> competences;

    public Task() {
    }

    public Task(Long id, String maxRating, String minRating, Level level, Course course, List<Competence> competences) {
        this.id = id;
        this.maxRating = maxRating;
        this.minRating = minRating;
        this.level = level;
        this.course = course;
        this.competences = competences;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaxRating() {
        return maxRating;
    }

    public void setMaxRating(String maxRating) {
        this.maxRating = maxRating;
    }

    public String getMinRating() {
        return minRating;
    }

    public void setMinRating(String minRating) {
        this.minRating = minRating;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<Competence> getCompetences() {
        return competences;
    }

    public void setCompetences(List<Competence> competences) {
        this.competences = competences;
    }
}