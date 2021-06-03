package com.diplom.padding.entity.app;

import javax.persistence.*;

@Entity
public class Role {
    @Id
    private Byte id;
    private String title;

    public Role(){
    }

    public Role(Byte id, String title) {
        this.id = id;
        this.title = title;
    }

    public Byte getId() {
        return id;
    }

    public void setId(Byte id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}