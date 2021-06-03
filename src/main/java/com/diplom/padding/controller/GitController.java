package com.diplom.padding.controller;

import org.springframework.http.*;
import com.diplom.padding.Git.GitApp;
import com.diplom.padding.model.GitModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URISyntaxException;

@Controller
@RequestMapping("/api/file")
public class GitController {
    private final GitApp gitApp;

    @Autowired
    public GitController(GitApp gitApp) {
        this.gitApp = gitApp;
    }

    @PostMapping(value="/upload")
    public @ResponseBody
    ResponseEntity<String> fileUpload(@RequestParam("file") MultipartFile file, GitModel gitModel) throws GitAPIException, IOException, URISyntaxException {
        gitApp.gitClone(gitModel, file);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value="/merge")
    public @ResponseBody
    ResponseEntity<String> initMerge(GitModel gitModel) throws GitAPIException, IOException, URISyntaxException {
        gitApp.gitMerge(gitModel);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value="/delete")
    public @ResponseBody
    ResponseEntity<String> deleteBranch(GitModel gitModel) throws GitAPIException, IOException, URISyntaxException {
        //gitApp.deleteBranch(gitModel);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}