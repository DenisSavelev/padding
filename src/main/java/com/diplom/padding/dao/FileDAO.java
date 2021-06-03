package com.diplom.padding.dao;

import com.diplom.padding.entity.app.File;
import com.diplom.padding.entity.moodle.FileMoodle;

import java.util.*;

public interface FileDAO {
    Optional<File> getById(Long id);
    List<File> saveAll(List<File> files);
    List<FileMoodle> findAll();
    List<Long> getIdByUserAndTask(Long idUser, Long idTask);
}