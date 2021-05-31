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
    @Column(name = "iteminstance")
    private Long idItem;
    @Column(name = "timemodified")
    private Long modified;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Float getMaxRating() {
        return maxRating;
    }

    public Float getMinRating() {
        return minRating;
    }

    public Long getIdCourse() {
        return idCourse;
    }

    public String getLevel() {
        return level;
    }

    public Long getIdItem() {
        return idItem;
    }
}