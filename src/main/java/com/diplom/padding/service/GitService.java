package com.diplom.padding.service;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.Repository;
import com.diplom.padding.model.GitModel;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public interface GitService {
    void createRepo(String discipline) throws IOException, GitAPIException, URISyntaxException;
    Repository openRepo(MultipartFile multipartFile) throws IOException;
    Git gitClone(GitModel gitModel) throws GitAPIException, IOException, URISyntaxException;
    void gitMerge(GitModel gitModel) throws GitAPIException, IOException, URISyntaxException;
    void deleteBranch(Git git, GitModel gitModel) throws GitAPIException, URISyntaxException;
    void updateBranch(GitModel gitModel) throws GitAPIException, IOException, URISyntaxException;
}