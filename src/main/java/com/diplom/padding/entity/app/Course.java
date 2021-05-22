package com.diplom.padding.entity.app;

import javax.persistence.*;

@Entity
public class Course {
    @Id
    private Long id;
    private String fullName;
    private String shortName;

    public Course() {
    }

    public Course(Long id, String fullName, String shortName) {
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