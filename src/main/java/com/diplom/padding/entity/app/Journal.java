package com.diplom.padding.entity.app;

import com.diplom.padding.entity.moodle.JournalMoodle;

import javax.persistence.*;
import java.util.List;

@Entity
public class Journal {
    @Id
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
    @ManyToOne(fetch = FetchType.EAGER)
    private Task task;
    private Float rating;
    @OneToMany(fetch = FetchType.EAGER)
    private List<File> files;

    public Journal() {
    }

    public Journal(JournalMoodle journal, Task task, List<File> files) {
        this.id = journal.getId();
        this.user = new User(journal.getUser());
        this.task = task;
        this.rating = journal.getGrade();
        this.files = files;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }
}