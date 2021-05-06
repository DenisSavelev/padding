package com.diplom.padding.Git;


import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;

import java.io.File;

public class GitApp {

    Git git = Git.cloneRepository()
            .setURI("http://192.168.0.104/root/IIE.git")
            .setDirectory(new File("/Рабочий стол/local"))
            .call();

    public GitApp() throws GitAPIException {
    }
}
