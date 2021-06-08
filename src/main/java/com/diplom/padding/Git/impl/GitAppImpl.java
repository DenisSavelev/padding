package com.diplom.padding.Git.impl;

import org.eclipse.jgit.api.*;
import org.eclipse.jgit.lib.*;
import org.eclipse.jgit.transport.*;
import com.diplom.padding.Git.GitApp;
import com.diplom.padding.model.GitModel;
import org.springframework.stereotype.Service;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.springframework.web.multipart.MultipartFile;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.springframework.beans.factory.annotation.Value;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.*;

@Service
public class GitAppImpl implements GitApp {
    private int i = 1;
    @Value("${spring.cloud.config.server.git.username}")
    private String USERNAME;
    @Value("${spring.cloud.config.server.git.password}")
    private String PASSWORD;
    @Value("${spring.cloud.config.server.git.uri}")
    private String URI;

    @Override
    public void createRepo(String discipline) throws GitAPIException, URISyntaxException {
        Git git = Git.init().setDirectory(new File("~/local")).call();
        git.getRepository().getRemoteName(discipline);
        RemoteAddCommand remoteAddCommand = git.remoteAdd();
        remoteAddCommand.setName("refs/heads/master");
        remoteAddCommand.setUri(new URIish(URI)); //дописать имя репозитоиря соответсвующего дисциплине
        remoteAddCommand.call();
        PushCommand push = git.push();
        push.setCredentialsProvider(new UsernamePasswordCredentialsProvider(USERNAME, PASSWORD));
        push.call();
    }

    private void createBranch(Git git, String branchName) throws GitAPIException {
        CreateBranchCommand bcc = git.branchCreate();
        CheckoutCommand checkout = git.checkout();
        bcc.setName(branchName)
                .setUpstreamMode(CreateBranchCommand.SetupUpstreamMode.SET_UPSTREAM)
                .setForce(true)
                .call();
        checkout.setName(branchName).call();
    }

    @Override
    public Repository openRepo(MultipartFile multipartFile) throws IOException {
        return new FileRepositoryBuilder()
                .setGitDir(new File("~/" + multipartFile))
                .build();
    }

    @Override
    public Git gitClone(GitModel gitModel) throws GitAPIException, IOException, URISyntaxException {
        String dest = "~/local";
        Git git = Git.cloneRepository()
                .setURI(URI + gitModel.getDiscipline() + ".git")
                .setBranch("master")
                .setDirectory(new File(dest))
                .call();
        String branch = gitModel.getTaskName().replaceAll(" ", "") + "/"
                + gitModel.getUserName().replaceAll(" ", "");
        createBranch(git, branch);
        gitModel.getFiles().forEach(file -> {
            Path to = Paths.get(dest + "/" + gitModel.getOrigName().remove(0));
            File copy = new File(to.toString());
            try {
                Files.copy(file.toPath(), copy.toPath());
                gitAdd(git, copy.getName());
            } catch (GitAPIException | IOException e) {
                e.printStackTrace();
            }
        });
        gitCommit(git);
        gitPush(git, gitModel.getDiscipline());
        return git;
    }

    @Override
    public void deleteBranch(Git git, GitModel gitModel) throws GitAPIException {
        String branch = gitModel.getTaskName().replaceAll(" ", "") + "/" + gitModel.getUserName().replaceAll(" ", "");
        RefSpec refSpec = new RefSpec()
                .setSource(null)
                .setDestination("refs/heads/" + branch);
        git.push()
                .setCredentialsProvider(new UsernamePasswordCredentialsProvider(USERNAME, PASSWORD))
                .setRefSpecs(refSpec)
                .setRemote("origin").call();
    }

    @Override
    public void gitMerge(GitModel gitModel) throws GitAPIException, IOException, URISyntaxException {
        String dest = "~/local";
        String branch = gitModel.getTaskName().replaceAll(" ", "")
                + "/"+gitModel.getUserName().replaceAll(" ", "");
        Git git = Git.cloneRepository()
                .setURI(URI + gitModel.getDiscipline() + ".git")
                .setBranch("master")
                .setDirectory(new File(dest))
                .call();
        ObjectId objectId = git.getRepository().resolve("origin/" + branch);
        git.merge().setCommit(true).include(objectId).call();
        deleteBranch(git, gitModel);
        gitPush(git, gitModel.getDiscipline());
    }

    private void gitAdd(Git git, String to) throws GitAPIException {
        AddCommand add = git.add();
        add.addFilepattern(to).call();
    }

    private void gitCommit(Git git) throws GitAPIException {
        CommitCommand commit = git.commit();
        i = i + 1;
        commit.setMessage("commit-" + i).call();
    }

    private void gitPush(Git git, String nameDiscipline) throws GitAPIException, URISyntaxException {
        RemoteAddCommand remoteAddCommand = git.remoteAdd();
        remoteAddCommand.setName("origin");
        remoteAddCommand.setUri(new URIish(URI + nameDiscipline + ".git")); //дописать имя репозитоиря соответсвующего дисциплине
        remoteAddCommand.call();
        PushCommand push = git.push();
        push.setCredentialsProvider(new UsernamePasswordCredentialsProvider(USERNAME, PASSWORD));
        push.call();
    }

    public void moveFile(String src, String dest) {
        Path result = null;
        try {
            result =  Files.move(Paths.get(src), Paths.get(dest));
        } catch (IOException e) {
            System.out.println("Exception while moving file: " + e.getMessage());
        }
        if (result != null) {
            System.out.println("File moved successfully.");
        } else {
            System.out.println("File movement failed.");
        }
    }
}