package com.diplom.padding.entity.app;

import com.diplom.padding.entity.moodle.FileMoodle;

import javax.persistence.*;

@Entity
public class File {
    @Id
    private Long id;
    private String title;
    private String path;
    @ManyToOne
    private User user;

    public File() {
    }

    public File(FileMoodle fileMoodle, User user) {
        this.id = fileMoodle.getId();
        this.title = fileMoodle.getName();
        this.path = fileMoodle.getContenthash().substring(0, 2) + "/" + fileMoodle.getContenthash().substring(2, 4);
        this.user = user;
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}