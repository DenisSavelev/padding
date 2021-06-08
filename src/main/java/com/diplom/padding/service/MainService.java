package com.diplom.padding.service;

import com.diplom.padding.dao.*;
import com.diplom.padding.Git.GitApp;
import com.diplom.padding.entity.app.*;
import com.diplom.padding.model.GitModel;
import com.diplom.padding.entity.moodle.*;
import org.springframework.stereotype.Service;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.io.File;
import java.nio.file.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.net.URISyntaxException;
import javax.annotation.PostConstruct;
import java.nio.file.attribute.BasicFileAttributes;

@Service
public class MainService {
    private final GitApp git;
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

    @Autowired
    public MainService(GitApp git, RoleDAO roleDAO, UserDAO userDAO, TaskDAO taskDAO, FileDAO fileDAO, CourseDAO courseDAO,
                       JournalDAO journalDAO, CompetenceDAO competenceDAO, Competence2DAO competence2DAO, CourseTaskMoodleDAO
                                   courseTaskMoodleDAO, CourseTaskCompetenceMoodleDAO courseTaskCompetenceMoodleDAO) {
        this.git = git;
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
    public void findAllDate() {
        List<Role> roles = new ArrayList<>();
        String[] title = new String[] {"Admin", "Teacher", "Student", "ManagerCompetency"};
        for(byte i = 0; i < 4; i++) {
           roles.add(new Role((byte) (i+1), title[i]));
        }
        roleDAO.saveAll(roles);

        List<User> users = new ArrayList<>();
        userDAO.findAll().forEach(userMoodle -> users.add(new User(userMoodle)));
        userDAO.saveAll(users);

        List<com.diplom.padding.entity.app.File> files = new ArrayList<>();
        fileDAO.findAll().forEach(file -> files.add(new com.diplom.padding.entity.app.File(file)));
        fileDAO.saveAll(files);

        List<Course> courses = new ArrayList<>();
        courseDAO.findAll().forEach(courseMoodle -> courses.add(new Course(courseMoodle)));
        courseDAO.saveAll(courses);

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
            competenceDAO.getById(competenceM2.getIdCompetence()).ifPresent(c -> competences2.add(new Competence2(competenceM2, c)));
        });
        competence2DAO.saveAllCompetence2(competences2);

        List<Competence3> competences3 = new ArrayList<>();
        List<CompetenceMoodle2> competenceMs3 = competence2DAO.findAllCompetence3();
        competenceMs3.forEach(competenceM3 -> {
            competenceM3.setDescription(parse(competenceM3.getDescription()));
            competence2DAO.getCompetence2ById(competenceM3.getIdParent()).ifPresent(c -> competences3.add(new Competence3(competenceM3, c)));
        });
        competence2DAO.saveAllCompetence3(competences3);

        List<Task> tasks = new ArrayList<>();
        taskDAO.findAll().forEach(taskMoodle ->
                courseTaskMoodleDAO.getIdByIdTaskAndIdCourse(taskMoodle.getIdItem(), taskMoodle.getIdCourse()).forEach(cmid -> {
            List<Competence2> competence2s = new ArrayList<>();
            List<Competence3> competence3s = new ArrayList<>();
            courseTaskCompetenceMoodleDAO.getCompetenceByIdCourseTask(cmid).forEach(id -> {
                competence2DAO.getCompetence2ById(id).ifPresent(competence2s::add);
                competence2DAO.getCompetence3ById(id).ifPresent(competence3s::add);
            });
            courseDAO.getById(taskMoodle.getIdCourse()).ifPresent(t -> tasks.add(new Task(taskMoodle, t, competence2s, competence3s)));
        }));
        taskDAO.saveAll(tasks);

        List<Journal> journals = getJournals(journalDAO.findAll());
        journalDAO.saveAll(journals);
    }

    @Scheduled(fixedDelay = 3600000)
    public void exportData() {
        SimpleDateFormat hour = new SimpleDateFormat("HH");
        if (hour.format(new Date()).equals("05")) {
            Queue<Journal> journals = new LinkedList<>(getJournals(journalDAO.findForTheDay()));
            journals.forEach(journal -> {
                if (journal.getFiles().size() > 0) {
                    com.diplom.padding.entity.app.File file = journal.getFiles().get(0);
                    String path = "/var/www/moodledata/filedir/" + file.getPath() + "/" + file.getHash();
                    try {
                        deleteDirectory();
                        git.gitClone(new GitModel(journal, new File(path)));
                    } catch (GitAPIException | URISyntaxException | IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    private void deleteDirectory() throws IOException {
        Path directory = Paths.get("~/");
        if (Files.exists(directory)) {
            Files.walkFileTree(directory, new SimpleFileVisitor<>() {
                @Override
                public FileVisitResult visitFile(Path path, BasicFileAttributes basicFileAttributes) throws IOException {
                    Files.delete(path);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path directory, IOException ioException) throws IOException {
                    Files.delete(directory);
                    return FileVisitResult.CONTINUE;
                }
            });
        }
    }

    private String parse(String description) {
        try {
            description = description.split(";\">")[1];
            return description.split("<br")[0];
        } catch (ArrayIndexOutOfBoundsException e) {
            return description;
        }
    }

    private List<Journal> getJournals(List<JournalMoodle> journalMoodles) {
        List<Journal> journals = new ArrayList<>();
        journalMoodles.forEach(journalMoodle ->
                userDAO.getById(journalMoodle.getIdUser()).ifPresent(user ->
                        taskDAO.getById(journalMoodle.getIdTask()).ifPresent(task ->
                                journals.add(new Journal(journalMoodle, user, task,
                                        task.getType().equals("assign") ? fileDAO.getByItemAndUser(taskDAO.getIdFilesByIdTask(task.getId()), user.getId()) : null)))));
        return journals;
    }
}