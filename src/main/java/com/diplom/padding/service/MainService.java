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
    private final FileDAO fileDAO;
    private final CourseDAO courseDAO;
    private final JournalDAO journalDAO;
    private final CompetenceDAO competenceDAO;
    private final Competence2DAO competence2DAO;
    private final CourseTaskMoodleDAO courseTaskMoodleDAO;
    private final CourseTaskCompetenceMoodleDAO courseTaskCompetenceMoodleDAO;

    /*@Scheduled(fixedDelay = 3600000)
    public void exportData() {
        SimpleDateFormat hour = new SimpleDateFormat("HH");
        if (hour.format(new Date()).equals("05")) {
        }
    }*/

    @Autowired
    public MainService(RoleDAO roleDAO, UserDAO userDAO, TaskDAO taskDAO, FileDAO fileDAO, CourseDAO courseDAO, JournalDAO journalDAO,
                       CompetenceDAO competenceDAO, Competence2DAO competence2DAO,
                       CourseTaskMoodleDAO courseTaskMoodleDAO, CourseTaskCompetenceMoodleDAO courseTaskCompetenceMoodleDAO) {
        this.roleDAO = roleDAO;
        this.userDAO = userDAO;
        this.taskDAO = taskDAO;
        this.fileDAO = fileDAO;
        this.courseDAO = courseDAO;
        this.journalDAO = journalDAO;
        this.competenceDAO = competenceDAO;
        this.competence2DAO = competence2DAO;
        this.courseTaskMoodleDAO = courseTaskMoodleDAO;
        this.courseTaskCompetenceMoodleDAO = courseTaskCompetenceMoodleDAO;
    }

    @PostConstruct
    public void startDate() {
        List<Role> roles = new ArrayList<>();
        String[] title = new String[] {"Admin", "Teacher", "Student", "ManagerCompetency"};
        for(byte i = 0; i < 4; i++) {
           roles.add(new Role((byte) (i+1), title[i]));
        }
        roleDAO.saveAll(roles);

        List<User> users = new ArrayList<>();
        userDAO.findAll().forEach(userMoodle -> users.add(new User(userMoodle)));
        userDAO.saveAll(users);

        List<File> files = new ArrayList<>();
        fileDAO.findAll().parallelStream().forEach(file -> files.add(new File(file)));
        fileDAO.saveAll(files);

        List<Course> courses = new ArrayList<>();
        courseDAO.findAll().parallelStream().forEach(courseMoodle -> courses.add(new Course(courseMoodle)));
        courseDAO.saveAll(courses);

        List<Competence> competences = new ArrayList<>();
        List<CompetenceMoodle> competenceMs = competenceDAO.findAll();
        competenceMs.parallelStream().forEach(competenceM -> {
            competenceM.setDescription(parse(competenceM.getDescription()));
            competences.add(new Competence(competenceM));
        });
        competenceDAO.saveAll(competences);

        List<Competence2> competences2 = new ArrayList<>();
        List<CompetenceMoodle2> competenceMs2 = competence2DAO.findAllCompetence2();
        competenceMs2.parallelStream().forEach(competenceM2 -> {
            competenceM2.setDescription(parse(competenceM2.getDescription()));
            competenceDAO.getById(competenceM2.getIdCompetence()).ifPresent(c -> competences2.add(new Competence2(competenceM2, c)));
        });
        competence2DAO.saveAllCompetence2(competences2);

        List<Competence3> competences3 = new ArrayList<>();
        List<CompetenceMoodle2> competenceMs3 = competence2DAO.findAllCompetence3();
        competenceMs3.parallelStream().forEach(competenceM3 -> {
            competenceM3.setDescription(parse(competenceM3.getDescription()));
            competence2DAO.getCompetence2ById(competenceM3.getIdParent()).ifPresent(c -> competences3.add(new Competence3(competenceM3, c)));
        });
        competence2DAO.saveAllCompetence3(competences3);

        List<Task> tasks = new ArrayList<>();
        taskDAO.findAll().parallelStream().forEach(taskMoodle -> courseTaskMoodleDAO.getIdByIdTaskAndIdCourse(taskMoodle.getIdItem(), taskMoodle.getIdCourse()).forEach(cmid -> {
            List<Competence2> competence2s = new ArrayList<>();
            List<Competence3> competence3s = new ArrayList<>();
            courseTaskCompetenceMoodleDAO.getCompetenceByIdCourseTask(cmid).forEach(id -> {
                competence2DAO.getCompetence2ById(id).ifPresent(competence2s::add);
                competence2DAO.getCompetence3ById(id).ifPresent(competence3s::add);
            });
            courseDAO.getById(taskMoodle.getIdCourse()).ifPresent(t -> tasks.add(new Task(taskMoodle, t, competence2s, competence3s)));
        }));
        taskDAO.saveAll(tasks);

        List<Journal> journals = new ArrayList<>();
        /*journalDAO.findAll().parallelStream().forEach(journalMoodle -> {

        });*/
        journalDAO.saveAll(journals);
    }

    private String parse(String description) {
        try {
            description = description.split(";\">")[1];
            return description.split("<br")[0];
        } catch (ArrayIndexOutOfBoundsException e) {
            return description;
        }
    }
}