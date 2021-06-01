package com.diplom.padding.entity.app;

import com.diplom.padding.entity.moodle.TaskMoodle;

import javax.persistence.*;
import java.util.List;

@Entity
public class Task {
    @Id
    private Long id;
    private String name;
    private Float maxRating;
    private Float minRating;
    @ManyToOne
    private Level level;
    @ManyToOne
    private Course course;
    @ManyToMany
    List<Competence2> competences2;
    @ManyToMany
    List<Competence3> competences3;

    public Task() {
    }

    public Task(TaskMoodle task, Level level, Course course, List<Competence2> competence2, List<Competence3> competence3) {
        this.id = task.getId();
        this.name = task.getName();
        this.maxRating = task.getMaxRating();
        this.minRating = task.getMinRating();
        this.level = level;
        this.course = course;
        this.competences2 = competence2;
        this.competences3 = competence3;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getMaxRating() {
        return maxRating;
    }

    public void setMaxRating(Float maxRating) {
        this.maxRating = maxRating;
    }

    public Float getMinRating() {
        return minRating;
    }

    public void setMinRating(Float minRating) {
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

    public List<Competence2> getCompetences2() {
        return competences2;
    }

    public void setCompetences2(List<Competence2> competences2) {
        this.competences2 = competences2;
    }

    public List<Competence3> getCompetences3() {
        return competences3;
    }

    public void setCompetences3(List<Competence3> competences3) {
        this.competences3 = competences3;
    }
}