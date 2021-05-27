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

    public CompetenceMoodle2() {

    }

    public CompetenceMoodle2(Long id, String shortname, String description, Long idCompetence, Long idParent) {
        this.id = id;
        this.shortname = shortname;
        this.description = description;
        this.idCompetence = idCompetence;
        this.idParent = idParent;
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

    public Long getIdCompetence() {
        return idCompetence;
    }

    public void setIdCompetence(Long idCompetence) {
        this.idCompetence = idCompetence;
    }

    public Long getIdParent() {
        return idParent;
    }

    public void setIdParent(Long idParent) {
        this.idParent = idParent;
    }
}