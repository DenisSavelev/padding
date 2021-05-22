package com.diplom.padding.repositories.app;

import com.diplom.padding.entity.app.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}