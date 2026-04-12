package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "documents")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Enumerated(EnumType.STRING)
    private DocumentType type;

    @Column(columnDefinition = "TEXT")
    private String body;

    private LocalDate creationDate;

    private LocalDate signDate;

    private String username;

    public Document() {}

    public Document(String title, DocumentType type, String body,
                    LocalDate creationDate, LocalDate signDate, String username) {
        this.title = title;
        this.type = type;
        this.body = body;
        this.creationDate = creationDate;
        this.signDate = signDate;
        this.username = username;
    }

    // getters & setters (Alt + Insert)

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public DocumentType getType() {
        return type;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public LocalDate getSignDate() {
        return signDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setSignDate(LocalDate signDate) {
        this.signDate = signDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public void setType(DocumentType type) {
        this.type = type;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}