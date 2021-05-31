package com.diplom.padding.service;

import com.diplom.padding.dao.*;
import com.diplom.padding.entity.app.*;
import com.diplom.padding.entity.moodle.*;
import org.springframework.stereotype.Service;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class MainService {
    private final RoleDAO roleDAO;
    private final UserDAO userDAO;
    private final CourseDAO courseDAO;
    private final CompetenceDAO competenceDAO;

    /*@Scheduled(fixedDelay = 3600000)
    public void exportData() {
        SimpleDateFormat hour = new SimpleDateFormat("HH");
        if (hour.format(new Date()).equals("05")) {
        }
    }*/

    @Autowired
    public MainService(RoleDAO roleDAO, UserDAO userDAO, CourseDAO courseDAO, CompetenceDAO competenceDAO) {
        this.roleDAO = roleDAO;
        this.userDAO = userDAO;
        this.courseDAO = courseDAO;
        this.competenceDAO = competenceDAO;
    }

    @PostConstruct
    public void startDate() {
        List<Role> roles = new ArrayList<>();
        roleDAO.findAll().forEach(roleMoodle -> roles.add(new Role(roleMoodle)));
        roleDAO.saveAll(roles);

        List<Course> courses = new ArrayList<>();
        courseDAO.findAll().forEach(courseMoodle -> courses.add(new Course(courseMoodle)));
        courseDAO.saveAll(courses);

        List<User> users = new ArrayList<>();
        userDAO.findAll().forEach(userMoodle -> users.add(new User(userMoodle)));
        userDAO.saveAll(users);

        List<Competence> competences = new ArrayList<>();
        List<CompetenceMoodle> competenceMs = competenceDAO.findAll();
        competenceMs.forEach(competenceM -> {
            competenceM.setDescription(parse(competenceM.getDescription()));
            competences.add(new Competence(competenceM));
        });
        competenceDAO.saveAll(competences);
    }

    private String parse(String description) {
        description = description.split(";\">")[1];
        return description.split("<br")[0];
    }
}