package com.diplom.padding.entity.moodle;

import javax.persistence.*;

@Entity
@Table(name = "quiz_grades")
public class QuizJournalMoodle {
    @Id
    private Long id;
    @Column(name = "userid")
    private Long idUser;
    @Column(name = "grade")
    private Float grade;
    @Column(name = "timemodified")
    private Long modified;

    public Long getId() {
        return id;
    }

    public Long getIdUser() {
        return idUser;
    }

    public Float getGrade() {
        return grade;
    }
}