package com.diplom.padding.entity.moodle;

import javax.persistence.*;

@Entity
@Table(name = "course_modules")
public class CourseTaskMoodle {
    @Id
    private Long id;
    @Column(name = "course")
    private Long idCourse;
    @Column(name = "instance")
    private Long idTask;
    @Column(name = "added")
    private Long added;

    public Long getId() {
        return id;
    }

    public Long getIdCourse() {
        return idCourse;
    }

    public Long getIdTask() {
        return idTask;
    }
}