package com.example.demo.repository;

import com.example.demo.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface DocumentRepository extends JpaRepository<Document, Long> {

    List<Document> findByUsername(String username);

    List<Document> findByUsernameAndSignDateIsNull(String username);

    List<Document> findByUsernameAndSignDateIsNotNull(String username);

    List<Document> findByCreationDateBetween(LocalDate from, LocalDate to);
}
