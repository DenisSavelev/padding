package com.diplom.padding.entity.app;

import javax.persistence.*;

@Entity
public class Competence2 {
    @Id
    private Long id;
    private String shortname;
    private String description;
    @ManyToOne
    private Competence competence;

    public Competence2() {
    }

    public Competence2(Long id, String shortname, String description, Competence competence) {
        this.id = id;
        this.shortname = shortname;
        this.description = description;
        this.competence = competence;
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

    public Competence getCompetence() {
        return competence;
    }

    public void setCompetence(Competence competence) {
        this.competence = competence;
    }
}