package com.diplom.padding.dao;

import com.diplom.padding.entity.app.File;
import com.diplom.padding.entity.moodle.FileMoodle;

import java.util.List;

public interface FileDAO {
    List<File> getByUserAndTask(Long idUser, Long idTask);
    List<File> saveAll(List<File> files);
    List<FileMoodle> findAll();
    List<FileMoodle> findForTheDay();
}