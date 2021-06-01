package com.diplom.padding.entity.app;

import com.diplom.padding.entity.moodle.CompetenceMoodle2;

import javax.persistence.*;

@Entity
public class Competence3 {
    @Id
    private Long id;
    private String shortname;
    private String description;
    @ManyToOne
    private Competence2 competence2;

    public Competence3() {
    }

    public Competence3(CompetenceMoodle2 competenceMoodle2, Competence2 competence) {
        this.id = competenceMoodle2.getId();
        this.shortname = competenceMoodle2.getShortname();
        this.description = competenceMoodle2.getDescription();
        this.competence2 = competence;
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

    public Competence2 getCompetence2() {
        return competence2;
    }

    public void setCompetence2(Competence2 competence2) {
        this.competence2 = competence2;
    }
}