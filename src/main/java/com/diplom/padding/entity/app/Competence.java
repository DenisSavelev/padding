package com.diplom.padding.entity.app;

import com.diplom.padding.entity.moodle.CompetenceMoodle;
import javax.persistence.*;

@Entity
public class Competence {
    @Id
    private Long id;
    private String shortname;
    private String description;

    public Competence() {
    }

    public Competence(CompetenceMoodle competenceMoodle) {
        this.id = competenceMoodle.getId();
        this.shortname = competenceMoodle.getShortname();
        this.description = competenceMoodle.getDescription();
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