package com.diplom.padding.Git;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.Repository;
import com.diplom.padding.model.GitModel;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URISyntaxException;

public interface GitApp {
    void createRepo(MultipartFile src) throws IOException, GitAPIException;
    Repository openRepo(MultipartFile multipartFile) throws IOException;
    Git gitClone(GitModel gitModel, MultipartFile src) throws GitAPIException, IOException, URISyntaxException;
    void gitAdd(Git git, String to) throws GitAPIException;
    void gitCommit(Git git) throws GitAPIException;
    void gitPush(Git git, String nameDiscipline) throws GitAPIException, URISyntaxException;
    void gitMerge(GitModel gitModel) throws GitAPIException, IOException, URISyntaxException;
    void deleteBranch(Git git, GitModel gitModel) throws GitAPIException, URISyntaxException;
}