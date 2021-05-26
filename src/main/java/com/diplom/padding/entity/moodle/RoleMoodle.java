package com.diplom.padding.entity.moodle;

import javax.persistence.*;

@Entity
@Table(name = "role")
public class RoleMoodle {
    @Id
    private Long id;
    @Column(name = "shortname")
    private String name;

    public RoleMoodle() {
    }

    public RoleMoodle(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}