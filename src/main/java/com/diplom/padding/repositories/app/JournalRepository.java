package com.diplom.padding.repositories.app;

import com.diplom.padding.entity.app.Journal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JournalRepository extends JpaRepository<Journal, Long> {
}