package com.diplom.padding.entity.moodle;

import javax.persistence.*;

@Entity
@Table(name = "grade_grades")
public class JournalMoodle {
    @Id
    private Long id;
    @Column(name = "itemid")
    private Long idTask;
    @Column(name = "userid")
    private Long idUser;
    @Column(name = "finalgrade")
    private Float grade;
    @Column(name = "timemodified")
    private Long modified;

    public Long getId() {
        return id;
    }

    public Long getIdTask() {
        return idTask;
    }

    public Long getIdUser() {
        return idUser;
    }

    public Float getGrade() {
        return grade;
    }
}