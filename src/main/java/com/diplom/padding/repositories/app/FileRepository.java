package com.diplom.padding.repositories.app;

import com.diplom.padding.entity.app.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long>  {
}