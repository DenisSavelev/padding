package com.diplom.padding.dao;

import com.diplom.padding.entity.app.File;
import com.diplom.padding.entity.moodle.FileMoodle;

import java.util.*;

public interface FileDAO {
    Optional<File> getById(Long id);
    List<File> deleteById(List<Long> id);
    List<File> getByItemAndUser(List<Long> idItem, Long idUser);
    List<File> saveAll(List<File> files);
    List<Long> getIdFiles();
    List<Long> getIdFilesMoodle();
    List<FileMoodle> findAll();
    List<FileMoodle> findForTheDay();
}