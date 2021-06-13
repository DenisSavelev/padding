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
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="competencyframeworkid")
    private CompetenceMoodle competence;
    @Column(name = "parentid")
    private Long idParent;
    @Column(name = "timemodified")
    private Long modified;

    public Long getId() {
        return id;
    }

    public String getShortname() {
        return shortname;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public CompetenceMoodle getCompetence() {
        return competence;
    }

    public Long getIdParent() {
        return idParent;
    }
}