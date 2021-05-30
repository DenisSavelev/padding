package com.diplom.padding.entity.moodle;

import javax.persistence.*;

@Entity
@Table(name = "competency")
public class CompetenceMoodle2 {
    @Id
    private Long id;
    @Column(name = "shortname")
    private String shortname;
    @Column(columnDefinition = "description")
    private String description;
    @Column(name = "competencyframeworkid")
    private Long idCompetence;
    @Column(name = "parentid")
    private Long idParent;
    @Column(name = "timemodified")
    private Long modified;

    public CompetenceMoodle2() {

    }

    public Long getId() {
        return id;
    }

    public String getShortname() {
        return shortname;
    }

    public String getDescription() {
        return description;
    }

    public Long getIdCompetence() {
        return idCompetence;
    }

    public Long getIdParent() {
        return idParent;
    }

    public Long getModified() {
        return modified;
    }
}