package com.diplom.padding.entity.app;

import com.diplom.padding.entity.moodle.RoleMoodle;
import javax.persistence.*;

@Entity
public class Role {
    @Id
    private Integer id;
    private String name;

    public Role() {
    }

    public Role(RoleMoodle roleMoodle) {
        this.id = Math.toIntExact(roleMoodle.getId());
        this.name = roleMoodle.getName();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}