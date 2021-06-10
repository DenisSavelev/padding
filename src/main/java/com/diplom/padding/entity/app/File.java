package com.diplom.padding.entity.app;

import com.diplom.padding.entity.moodle.FileMoodle;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class File {
    @Id
    private Long id;
    private String title;
    private String hash;
    private String path;
    private Long item;

    public File() {
    }

    public File(FileMoodle fileMoodle) {
        this.id = fileMoodle.getId();
        this.title = fileMoodle.getName();
        this.hash = fileMoodle.getContentHash();
        this.path = fileMoodle.getContentHash().substring(0, 2) + "/" + fileMoodle.getContentHash().substring(2, 4);
        this.item = fileMoodle.getIdItem();
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

    public Long getItem() {
        return item;
    }

    public void setItem(Long item) {
        this.item = item;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        File file = (File) o;
        return Objects.equals(id, file.id) && Objects.equals(title, file.title) && Objects.equals(hash, file.hash) && Objects.equals(path, file.path) && Objects.equals(item, file.item);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, hash, path, item);
    }
}