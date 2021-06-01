package com.diplom.padding.entity.app;

import javax.persistence.*;

@Entity
public class File {
    @Id
    private Long id;
    private String title;
    @ManyToOne
    private User user;

    public File() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}