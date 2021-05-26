package com.diplom.padding.repositories.app;

import com.diplom.padding.entity.app.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}