package com.diplom.padding.entity.app;

import javax.persistence.*;

@Entity
public class Competence {
    @Id
    private Long id;
    private String shortname;
    private String description;

    public Competence() {
    }

    public Competence(Long id, String shortname, String description) {
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