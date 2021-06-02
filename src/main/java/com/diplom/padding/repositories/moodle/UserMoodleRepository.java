package com.diplom.padding.repositories.moodle;

import com.diplom.padding.entity.moodle.UserMoodle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserMoodleRepository extends JpaRepository<UserMoodle, Long> {
}