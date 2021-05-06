package com.diplom.padding;


import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;


@SpringBootApplication
public class PaddingApp {
    public static void main(String[] args) throws GitAPIException {
        SpringApplication.run(PaddingApp.class, args);
        Git git = Git.cloneRepository()
                .setURI("http://192.168.0.104/root/IIE.git")
                .setDirectory(new File("~/local"))
                .call();
    }
}