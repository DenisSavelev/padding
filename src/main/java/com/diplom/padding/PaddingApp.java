package com.diplom.padding;


import com.diplom.padding.Git.GitApp;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Repository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;


@SpringBootApplication
public class PaddingApp {
    public static void main(String[] args) throws GitAPIException, IOException, URISyntaxException {
        SpringApplication.run(PaddingApp.class, args);
        GitApp gitApp = new GitApp();
        Git git = gitApp.gitClone("master");
        gitApp.gitMerge(git);
        //gitApp.createRepo(git.getRepository().getDirectory());
        /*Git git = gitApp.gitClone();
        gitApp.moveFile("check/TestFile.pdf", "~/local1/TestFile.pdf");*/




        //gitApp.teleport();
        /*gitApp.gitAdd(git, "TestFile.pdf");
        gitApp.gitCommit(git);
        gitApp.gitPush(git);*/
    }
}