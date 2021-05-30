package com.diplom.padding.entity.moodle;

import javax.persistence.*;

@Entity
@Table(name = "course")
public class CourseMoodle {
    @Id
    private Long id;
    @Column(name = "fullname")
    private String fullName;
    @Column(name = "shortname")
    private String shortName;
    @Column(name = "timemodified")
    private Long modified;

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getShortName() {
        return shortName;
    }

    public Long getModified() {
        return modified;
    }
}