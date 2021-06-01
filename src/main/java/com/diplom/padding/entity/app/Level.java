package com.diplom.padding.entity.app;

import javax.persistence.*;

@Entity
public class Level {
    @Id
    private Integer id;
    private String title;

    public Level() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}