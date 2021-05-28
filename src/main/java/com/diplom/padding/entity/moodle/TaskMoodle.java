package com.diplom.padding.entity.moodle;

import javax.persistence.*;

@Entity
@Table(name = "grade_items")
public class TaskMoodle {
    @Id
    private Long id;
    @Column(name = "itemname")
    private String name;
    @Column(name = "grademax")
    private Float maxRating;
    @Column(name = "grademin")
    private Float minRating;
    @Column(name = "courseid")
    private Long idCourse;
    @Column(name = "itemmodule")
    private String level;

    public TaskMoodle() {
    }

    public TaskMoodle(Long id, String name, Float maxRating, Float minRating, Long idCourse, String level) {
        this.id = id;
        this.name = name;
        this.maxRating = maxRating;
        this.minRating = minRating;
        this.idCourse = idCourse;
        this.level = level;
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

    public Long getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(Long idCourse) {
        this.idCourse = idCourse;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}