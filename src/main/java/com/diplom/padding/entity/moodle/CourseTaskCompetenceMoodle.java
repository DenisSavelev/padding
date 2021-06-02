package com.diplom.padding.entity.moodle;

import javax.persistence.*;

@Entity
@Table(name = "competency_modulecomp")
public class CourseTaskCompetenceMoodle {
    @Id
    private Long id;
    @Column(name = "cmid")
    private Long idCourseTask;
    @Column(name = "competencyid")
    private Long idCompetence;
    @Column(name = "timemodified")
    private Long modified;

    public Long getId() {
        return id;
    }

    public Long getIdCourseTask() {
        return idCourseTask;
    }

    public Long getIdCompetence() {
        return idCompetence;
    }
}