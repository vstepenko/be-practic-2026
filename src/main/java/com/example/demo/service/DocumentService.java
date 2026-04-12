package com.example.demo.service;

import com.example.demo.model.Document;
import com.example.demo.repository.DocumentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DocumentService {

    private final DocumentRepository repository;

    public DocumentService(DocumentRepository repository) {
        this.repository = repository;
    }

    public Document create(Document document) {
        return repository.save(document);
    }

    public Document update(Long id, Document newDoc) {
        Document doc = repository.findById(id).orElseThrow();

        doc.setTitle(newDoc.getTitle());
        doc.setBody(newDoc.getBody());
        doc.setType(newDoc.getType());
        doc.setSignDate(newDoc.getSignDate());

        return repository.save(doc);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<Document> getByUser(String username) {
        return repository.findByUsername(username);
    }

    public List<Document> getSigned(String username) {
        return repository.findByUsernameAndSignDateIsNotNull(username);
    }

    public List<Document> getUnsigned(String username) {
        return repository.findByUsernameAndSignDateIsNull(username);
    }

    public List<Document> getByDateRange(LocalDate from, LocalDate to) {
        return repository.findByCreationDateBetween(from, to);
    }
}