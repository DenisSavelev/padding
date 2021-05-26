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

    public CourseMoodle() {
    }

    public CourseMoodle(Long id, String fullName, String shortName) {
        this.id = id;
        this.fullName = fullName;
        this.shortName = shortName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
}