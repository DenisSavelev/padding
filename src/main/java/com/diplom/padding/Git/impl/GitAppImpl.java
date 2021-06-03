package com.diplom.padding.Git.impl;

import com.diplom.padding.Git.GitApp;
import com.diplom.padding.model.GitModel;
import org.eclipse.jgit.api.*;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.*;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.transport.RefSpec;
import org.eclipse.jgit.transport.URIish;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class GitAppImpl implements GitApp {
    int i = 1;

    public void createRepo(MultipartFile src) throws IOException, GitAPIException {
        Repository repo = new FileRepositoryBuilder().readEnvironment().findGitDir(convertMultiPartToFile(src)).build();
        Git git = new Git(repo);
        CreateBranchCommand bcc = git.branchCreate();
        CheckoutCommand checkout = git.checkout();
        String branchName = "branch" + (int) (Math.random() * 100);
        bcc.setName(branchName)
                .setUpstreamMode(CreateBranchCommand.SetupUpstreamMode.SET_UPSTREAM)
                .setForce(true)
                .call();
        checkout.setName(branchName).call();
    }

    public void createBranch(Git git, String branchName) throws GitAPIException {
        CreateBranchCommand bcc = git.branchCreate();
        CheckoutCommand checkout = git.checkout();
        bcc.setName(branchName)
                .setUpstreamMode(CreateBranchCommand.SetupUpstreamMode.SET_UPSTREAM)
                .setForce(true)
                .call();
        checkout.setName(branchName).call();
    }

    public Repository openRepo(MultipartFile multipartFile) throws IOException {
    Repository existingRepo = new FileRepositoryBuilder()
            .setGitDir(new File("~/" + multipartFile))
            .build();
    return existingRepo;
    }

    public Git gitClone(GitModel gitModel, MultipartFile src) throws GitAPIException, IOException, URISyntaxException {
        String dest = "~/local";
        Git git = Git.cloneRepository()
                .setURI("http://192.168.0.104/root/"+ gitModel.getDiscipline() +".git")
                .setBranch("master")
                .setDirectory(new File(dest))
                .call();
        String branch = gitModel.getTaskName().replaceAll(" ", "") +"/"+gitModel.getUserName().replaceAll(" ", "");
        createBranch(git, branch);
        Path to = Paths.get(dest+"/"+src.getOriginalFilename());
        src.transferTo(to);
        gitAdd(git, convertMultiPartToFile(src).getName());
        gitCommit(git);
        gitPush(git, gitModel.getDiscipline());
        return git;

    }
    public void gitAdd(Git git, String to) throws GitAPIException {
        AddCommand add = git.add();
        add.addFilepattern(to).call();
    }
    public void gitCommit(Git git) throws GitAPIException {
        CommitCommand commit = git.commit();
        i = i+1;
        commit.setMessage("commit-"+i).call();
    }

    public void gitPush(Git git, String nameDiscipline) throws GitAPIException, URISyntaxException {
        RemoteAddCommand remoteAddCommand = git.remoteAdd();
        remoteAddCommand.setName("origin");
        remoteAddCommand.setUri(new URIish("http://192.168.0.104/root/"+ nameDiscipline + ".git")); //дописать имя репозитоиря соответсвующего дисциплине
        remoteAddCommand.call();
        PushCommand push = git.push();
        push.setCredentialsProvider(new UsernamePasswordCredentialsProvider("root", "root1234"));
        push.call();
    }

    public void deleteBranch(Git git, GitModel gitModel) throws GitAPIException, URISyntaxException {
        String branch = gitModel.getTaskName().replaceAll(" ", "") +"/"+gitModel.getUserName().replaceAll(" ", "");
        RefSpec refSpec = new RefSpec()
                .setSource(null)
                .setDestination("refs/heads/"+branch);
        git.push()
                .setCredentialsProvider(new UsernamePasswordCredentialsProvider("root", "root1234"))
                .setRefSpecs(refSpec)
                .setRemote("origin").call();
    }

    public void gitMerge(GitModel gitModel) throws GitAPIException, IOException, URISyntaxException {
        String dest = "~/local";
        String branch = gitModel.getTaskName().replaceAll(" ", "") +"/"+gitModel.getUserName().replaceAll(" ", "");
        Git git = Git.cloneRepository()
                .setURI("http://192.168.0.104/root/"+ gitModel.getDiscipline() +".git")
                .setBranch("master")
                .setDirectory(new File(dest))
                .call();
        ObjectId objectId = git.getRepository().resolve("origin/"+ branch);
        git.merge().setCommit(true).include(objectId).call();
        deleteBranch(git, gitModel);
        gitPush(git, gitModel.getDiscipline());
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convertFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convertFile);
        fos.write(file.getBytes());
        fos.close();
        return convertFile;
    }

    public void moveFile(String src, String dest) {
        Path result = null;
        try {
            result =  Files.move(Paths.get(src), Paths.get(dest));
        } catch (IOException e) {
            System.out.println("Exception while moving file: " + e.getMessage());
        }
        if(result != null) {
            System.out.println("File moved successfully.");
        }else{
            System.out.println("File movement failed.");
        }
    }
}
