package com.diplom.padding.controller;


import com.diplom.padding.Git.GitApp;
import com.diplom.padding.Git.impl.GitAppImpl;
import com.diplom.padding.model.GitModel;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URISyntaxException;

@Controller
@RequestMapping("/api/file")
public class GitController {

    private GitApp gitApp;
    @Autowired
    public GitController(GitApp gitApp) {
        this.gitApp = gitApp;
    }

    @PostMapping(value="/upload")
    public @ResponseBody
    ResponseEntity<String> fileUpload(HttpServletRequest request, @RequestParam("file") MultipartFile file, GitModel gitModel) throws GitAPIException, IOException, URISyntaxException {
        gitApp.gitClone(gitModel, file);
        return new ResponseEntity<>( HttpStatus.OK);
    }

    @PostMapping(value="/merge")
    public @ResponseBody
    ResponseEntity<String> initMerge(HttpServletRequest request, GitModel gitModel) throws GitAPIException, IOException, URISyntaxException {
        gitApp.gitMerge(gitModel);
        return new ResponseEntity<>( HttpStatus.OK);
    }

    @PostMapping(value="/delete")
    public @ResponseBody
    ResponseEntity<String> deleteBranch(HttpServletRequest request, GitModel gitModel) throws GitAPIException, IOException, URISyntaxException {
        //gitApp.deleteBranch(gitModel);
        return new ResponseEntity<>( HttpStatus.OK);
    }
}
