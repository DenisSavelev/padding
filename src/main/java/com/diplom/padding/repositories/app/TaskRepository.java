package com.diplom.padding.repositories.app;

import com.diplom.padding.entity.app.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}