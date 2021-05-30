package com.diplom.padding.entity.moodle;

import javax.persistence.*;

@Entity
@Table(name = "role")
public class RoleMoodle {
    @Id
    private Long id;
    @Column(name = "shortname")
    private String name;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}