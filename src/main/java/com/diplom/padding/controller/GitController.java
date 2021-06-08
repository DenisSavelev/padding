package com.diplom.padding.controller;

import com.diplom.padding.service.GitService;
import org.springframework.http.*;
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
    private final GitService gitService;

    @Autowired
    public GitController(GitService gitService) {
        this.gitService = gitService;
    }

    @PostMapping(value="/upload")
    public @ResponseBody
    ResponseEntity<String> fileUpload(@RequestParam("file") MultipartFile file, GitModel gitModel) throws GitAPIException, IOException, URISyntaxException {
        //gitService.gitClone(gitModel, file);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value="/merge")
    public @ResponseBody
    ResponseEntity<String> initMerge(GitModel gitModel) throws GitAPIException, IOException, URISyntaxException {
      //  gitService.gitMerge(gitModel);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value="/create")
    public @ResponseBody
    ResponseEntity<String> deleteBranch(String discipline) throws GitAPIException, IOException, URISyntaxException {
      //  gitService.createRepo(discipline);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}