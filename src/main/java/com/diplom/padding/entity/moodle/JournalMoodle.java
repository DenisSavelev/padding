package com.diplom.padding.entity.moodle;

import javax.persistence.*;

@Entity
@Table(name = "grade_grades")
public class JournalMoodle {
    @Id
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="itemid")
    private TaskMoodle task;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="userid")
    private UserMoodle user;
    @Column(name = "finalgrade")
    private Float grade;
    @Column(name = "timemodified")
    private Long modified;

    public Long getId() {
        return id;
    }

    public TaskMoodle getTask() {
        return task;
    }

    public UserMoodle getUser() {
        return user;
    }

    public Float getGrade() {
        return grade;
    }
}