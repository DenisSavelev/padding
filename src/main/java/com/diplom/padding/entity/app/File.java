package com.diplom.padding.entity.app;

import com.diplom.padding.entity.moodle.FileMoodle;

import javax.persistence.*;

@Entity
public class File {
    @Id
    private Long id;
    private String title;
    private String hash;
    private String path;

    public File() {
    }

    public File(FileMoodle fileMoodle) {
        this.id = fileMoodle.getId();
        this.title = fileMoodle.getName();
        this.hash = fileMoodle.getContentHash();
        this.path = fileMoodle.getContentHash().substring(0, 2) + "/" + fileMoodle.getContentHash().substring(2, 4);
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

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}