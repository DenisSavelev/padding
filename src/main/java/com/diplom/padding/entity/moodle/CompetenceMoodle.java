package com.diplom.padding.entity.moodle;

import javax.persistence.*;

@Entity
@Table(name = "competency_framework")
public class CompetenceMoodle {
    @Id
    private Long id;
    @Column(name = "shortname")
    private String shortname;
    @Column(name = "description")
    private String description;
    @Column(name = "timemodified")
    private Long modified;

    public Long getId() {
        return id;
    }

    public String getShortname() {
        return shortname;
    }

    public String getDescription() {
        return description;
    }

    public Long getModified() {
        return modified;
    }
}