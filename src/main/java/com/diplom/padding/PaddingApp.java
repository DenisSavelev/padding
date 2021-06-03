package com.diplom.padding;


import org.eclipse.jgit.api.errors.GitAPIException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.URISyntaxException;


@SpringBootApplication
public class PaddingApp {
    public static void main(String[] args) throws GitAPIException, IOException, URISyntaxException {
        SpringApplication.run(PaddingApp.class, args);

        //gitApp.createRepo(git.getRepository().getDirectory());
        /*Git git = gitApp.gitClone();
        gitApp.moveFile("check/TestFile.pdf", "~/local1/TestFile.pdf");*/




        //gitApp.teleport();
        /*gitApp.gitAdd(git, "TestFile.pdf");
        gitApp.gitCommit(git);
        gitApp.gitPush(git);*/
    }
}