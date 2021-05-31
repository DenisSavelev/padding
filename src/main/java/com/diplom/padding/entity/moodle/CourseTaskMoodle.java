package com.diplom.padding.entity.moodle;

import javax.persistence.*;

@Entity
public class CourseTaskMoodle {
    @Id
    private Long id;
    @Column(name = "course")
    private Long idCourse;
    @Column(name = "instance")
    private Long idTask;
    @Column(name = "timemodified")
    private Long modified;

    public Long getId() {
        return id;
    }

    public Long getIdCourse() {
        return idCourse;
    }

    public Long getIdTask() {
        return idTask;
    }

    public Long getModified() {
        return modified;
    }
}