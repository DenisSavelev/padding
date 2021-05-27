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

    public CompetenceMoodle() {
    }

    public CompetenceMoodle(Long id, String shortname, String description) {
        this.id = id;
        this.shortname = shortname;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}