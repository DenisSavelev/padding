package com.diplom.padding.Git;



import org.eclipse.jgit.api.*;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.internal.ketch.RemoteGitReplica;
import org.eclipse.jgit.lib.*;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.transport.URIish;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GitApp{
    int i = 1;


    public void createRepo(File src) throws IOException, GitAPIException {
        Repository repo = new FileRepositoryBuilder().readEnvironment().findGitDir(src).build();
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





    public Repository openRepo() throws IOException {
    Repository existingRepo = new FileRepositoryBuilder()
            .setGitDir(new File("~/local1"))
            .build();
    return existingRepo;
    }

    public void moveFile(String src, String dest ) {
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

    public Git gitClone(String branch) throws GitAPIException {
        Git git = Git.cloneRepository()
                .setURI("http://192.168.0.104/root/IIE.git")
                .setBranch(branch)
                .setDirectory(new File("~/local2"))
                .call();
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

    public void gitPush(Git git) throws GitAPIException, IOException, URISyntaxException {
        RemoteAddCommand remoteAddCommand = git.remoteAdd();
        remoteAddCommand.setName("origin");
        remoteAddCommand.setUri(new URIish("http://192.168.0.104/root/IIE.git"));

        remoteAddCommand.call();
        PushCommand push = git.push();
        push.setCredentialsProvider(new UsernamePasswordCredentialsProvider("root", "root1234"));
        push.call();
    }

    public void gitMerge(Git git) throws GitAPIException, IOException, URISyntaxException {
        ObjectId objectId = git.getRepository().resolve("origin/branch3");
        git.merge().include(objectId).call();
        gitPush(git);
    }

}
