package com.diplom.padding.service;

import com.diplom.padding.dao.*;
import com.diplom.padding.entity.app.*;
import com.diplom.padding.entity.moodle.*;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class MainService {
    private final RoleDAO roleDAO;
    private final UserDAO userDAO;
    private final TaskDAO taskDAO;
    private final CourseDAO courseDAO;
    private final CompetenceDAO competenceDAO;
    private final Competence2DAO competence2DAO;

    /*@Scheduled(fixedDelay = 3600000)
    public void exportData() {
        SimpleDateFormat hour = new SimpleDateFormat("HH");
        if (hour.format(new Date()).equals("05")) {
        }
    }*/

    @Autowired
    public MainService(RoleDAO roleDAO, UserDAO userDAO, TaskDAO taskDAO, CourseDAO courseDAO,
                       CompetenceDAO competenceDAO, Competence2DAO competence2DAO) {
        this.roleDAO = roleDAO;
        this.userDAO = userDAO;
        this.taskDAO = taskDAO;
        this.courseDAO = courseDAO;
        this.competenceDAO = competenceDAO;
        this.competence2DAO = competence2DAO;
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

        List<Competence2> competences2 = new ArrayList<>();
        List<CompetenceMoodle2> competenceMs2 = competence2DAO.findAllCompetence2();
        competenceMs2.forEach(competenceM2 -> {
            competenceM2.setDescription(parse(competenceM2.getDescription()));
            competences2.add(new Competence2(competenceM2, competenceDAO.getById(competenceM2.getIdCompetence()).orElseThrow()));
        });
        competence2DAO.saveAllCompetence2(competences2);

        List<Competence3> competences3 = new ArrayList<>();
        List<CompetenceMoodle2> competenceMs3 = competence2DAO.findAllCompetence3();
        competenceMs3.forEach(competenceM3 -> {
            competenceM3.setDescription(parse(competenceM3.getDescription()));
            competences3.add(new Competence3(competenceM3, competence2DAO.getCompetence2ById(competenceM3.getIdCompetence()).orElseThrow()));
        });
        competence2DAO.saveAllCompetence3(competences3);

        List<Task> tasks = new ArrayList<>();
        taskDAO.findAll().forEach(taskMoodle -> tasks.add(new Task()));
        taskDAO.saveAll(tasks);
    }

    private String parse(String description) {
        description = description.split(";\">")[1];
        return description.split("<br")[0];
    }
}