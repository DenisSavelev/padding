package com.diplom.padding.entity.app;

import javax.persistence.*;

@Entity
public class RoleInCourse {
    @Id
    private Long id;
    @ManyToOne
    private User user;
    @ManyToOne
    private Role role;
    @ManyToOne
    private Course course;

    public RoleInCourse() {
    }

    public RoleInCourse(Long id, User user, Role role, Course course) {
        this.id = id;
        this.user = user;
        this.role = role;
        this.course = course;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}