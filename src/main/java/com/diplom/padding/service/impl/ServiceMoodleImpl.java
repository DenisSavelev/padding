package com.diplom.padding.service.impl;

import com.diplom.padding.dao.*;
import com.diplom.padding.entity.app.*;
import com.diplom.padding.service.GitService;
import com.diplom.padding.model.GitModel;
import com.diplom.padding.entity.moodle.*;
import org.springframework.stereotype.Service;
import com.diplom.padding.service.ServiceMoodle;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.*;
import java.io.File;
import java.nio.file.*;
import java.io.IOException;
import java.net.URISyntaxException;
import javax.annotation.PostConstruct;
import java.nio.file.attribute.BasicFileAttributes;

@Service
@EnableScheduling
public class ServiceMoodleImpl extends Thread implements ServiceMoodle {
    private final GitService git;
    private final RoleDAO roleDAO;
    private final UserDAO userDAO;
    private final TaskDAO taskDAO;
    private final FileDAO fileDAO;
    private final CourseDAO courseDAO;
    private final JournalDAO journalDAO;
    private static Queue<Journal> update;
    private static Queue<Journal> journals;
    private final CompetenceDAO competenceDAO;
    private final Competence2DAO competence2DAO;
    private final CourseTaskMoodleDAO courseTaskMoodleDAO;
    private final CourseTaskCompetenceMoodleDAO courseTaskCompetenceMoodleDAO;

    @Autowired
    public ServiceMoodleImpl(GitService git, RoleDAO roleDAO, UserDAO userDAO, TaskDAO taskDAO, FileDAO fileDAO,
                             CourseDAO courseDAO, JournalDAO journalDAO, CompetenceDAO competenceDAO,
                             Competence2DAO competence2DAO, CourseTaskMoodleDAO courseTaskMoodleDAO,
                             CourseTaskCompetenceMoodleDAO courseTaskCompetenceMoodleDAO) {
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
    private void findAllData() {
        List<Role> roles = new ArrayList<>();
        String[] title = new String[] {"Admin", "Teacher", "Student", "ManagerCompetency"};
        for(byte i = 0; i < 4; i++) {
           roles.add(new Role((byte) (i+1), title[i]));
        }
        roleDAO.saveAll(roles);
        updateUser(userDAO.findAll());
        updateFile(fileDAO.findAll());
        updateCourse(courseDAO.findAll());
        updateCompetence(competenceDAO.findAll());
        updateCompetence2(competence2DAO.findAllCompetence2());
        updateCompetence3(competence2DAO.findAllCompetence3());
        updateTask(taskDAO.findAll());
        journalDAO.saveAll(getJournals(journalDAO.findAll()));
    }

    @Scheduled(cron = "00 00 05 * * ?")
    private void exportDataForTheDay() {
        updateUser(userDAO.findForTheDay());
        updateFile(fileDAO.findForTheDay());
        updateCourse(courseDAO.findForTheDay());
        updateCompetence(competenceDAO.findForTheDay());
        updateCompetence2(competence2DAO.findCompetence2ForTheDay());
        updateCompetence3(competence2DAO.findCompetence3ForTheDay());
        updateTask(taskDAO.findForTheDay());
        journals = new LinkedList<>();
        update = new LinkedList<>();
        getJournals(journalDAO.findForTheDay()).forEach(journal -> {
            if (journalDAO.getById(journal.getId()).isPresent()) {
                update.add(journal);
            } else {
                journals.add(journal);
            }});
        journalDAO.saveAll(journals);
        journalDAO.saveAll(update);
        new Thread(new ServiceMoodleImpl(git, roleDAO, userDAO, taskDAO, fileDAO, courseDAO, journalDAO, competenceDAO,
                competence2DAO, courseTaskMoodleDAO, courseTaskCompetenceMoodleDAO)).start();
        interactionGit(update);
        update.clear();
    }

    @Override
    public void run() {
        interactionGit(journals);
        journals.clear();
    }

    public static void deleteDirectory(String path) throws IOException {
        Path directory = Paths.get(path);
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

    private void interactionGit(Queue<Journal> queue) {
        queue.forEach(journal -> {
            if (journal.getTask().getType().equals("assign")) {
                if (journal.getFiles().size() > 0) {
                    List<File> files = new ArrayList<>();
                    List<String> origName = new ArrayList<>();
                    journal.getFiles().forEach(file -> {
                        String path = "/var/www/moodledata/filedir/" + file.getPath() + "/" + file.getHash();
                        origName.add(file.getTitle());
                        files.add(new File(path));
                    });
                    try {
                        deleteDirectory("~/");
                        git.gitClone(new GitModel(journal, origName, files));
                    } catch (GitAPIException | URISyntaxException | IOException e) {
                        e.printStackTrace();
                    }
                }}});
        queue.forEach(journal -> {
            if (journal.getTask().getType().equals("assign")) {
                if (journal.getFiles().size() > 0) {
                    if (journal.getRating() / journal.getTask().getMaxRating() >= 0.5) {
                        try {
                            deleteDirectory("~/");
                            git.gitMerge(new GitModel(journal, null, null));
                        } catch (GitAPIException | URISyntaxException | IOException e) {
                            e.printStackTrace();
                        }
                    }
                }}});
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

    private void updateUser(List<UserMoodle> userMoodles) {
        List<User> users = new ArrayList<>();
        userMoodles.forEach(userMoodle -> users.add(new User(userMoodle)));
        userDAO.saveAll(users);
    }

    private void updateFile(List<FileMoodle> fileMoodles) {
        List<com.diplom.padding.entity.app.File> files = new ArrayList<>();
        fileMoodles.forEach(file -> files.add(new com.diplom.padding.entity.app.File(file)));
        fileDAO.saveAll(files);
    }

    private void updateCourse(List<CourseMoodle> courseMoodles) {
        List<Course> courses = new ArrayList<>();
        courseMoodles.forEach(courseMoodle -> courses.add(new Course(courseMoodle)));
        courseDAO.saveAll(courses);
    }

    private void updateCompetence(List<CompetenceMoodle> competenceMs) {
        List<Competence> competences = new ArrayList<>();
        competenceMs.forEach(competenceM -> {
            competenceM.setDescription(parse(competenceM.getDescription()));
            competences.add(new Competence(competenceM));
        });
        competenceDAO.saveAll(competences);
    }

    private void updateCompetence2(List<CompetenceMoodle2> competenceMs2) {
        List<Competence2> competences2 = new ArrayList<>();
        competenceMs2.forEach(competenceM2 -> {
            competenceM2.setDescription(parse(competenceM2.getDescription()));
            competenceDAO.getById(competenceM2.getIdCompetence()).ifPresent(c -> competences2.add(new Competence2(competenceM2, c)));
        });
        competence2DAO.saveAllCompetence2(competences2);
    }

    private void updateCompetence3(List<CompetenceMoodle2> competenceMs3) {
        List<Competence3> competences3 = new ArrayList<>();
        competenceMs3.forEach(competenceM3 -> {
            competenceM3.setDescription(parse(competenceM3.getDescription()));
            competence2DAO.getCompetence2ById(competenceM3.getIdParent()).ifPresent(c -> competences3.add(new Competence3(competenceM3, c)));
        });
        competence2DAO.saveAllCompetence3(competences3);
    }

    private void updateTask(List<TaskMoodle> taskMoodles) {
        List<Task> tasks = new ArrayList<>();
        taskMoodles.forEach(taskMoodle ->
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
    }
}