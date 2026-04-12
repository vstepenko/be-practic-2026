package com.example.demo.controller;

import com.example.demo.model.Document;
import com.example.demo.service.DocumentService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/documents")
public class DocumentController {

    private final DocumentService service;

    public DocumentController(DocumentService service) {
        this.service = service;
    }

    @PostMapping
    public Document create(@RequestBody Document document) {
        return service.create(document);
    }

    @PutMapping("/{id}")
    public Document update(@PathVariable Long id,
                           @RequestBody Document document) {
        return service.update(id, document);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/user/{username}")
    public List<Document> getByUser(@PathVariable String username) {
        return service.getByUser(username);
    }

    @GetMapping("/signed/{username}")
    public List<Document> getSigned(@PathVariable String username) {
        return service.getSigned(username);
    }

    @GetMapping("/unsigned/{username}")
    public List<Document> getUnsigned(@PathVariable String username) {
        return service.getUnsigned(username);
    }

    @GetMapping("/date")
    public List<Document> getByDate(
            @RequestParam LocalDate from,
            @RequestParam LocalDate to) {
        return service.getByDateRange(from, to);
    }
}