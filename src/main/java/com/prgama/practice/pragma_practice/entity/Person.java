package com.prgama.practice.pragma_practice.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "persons")
public class Person {

    @Id
    @Column(updatable = false, nullable = false)
    private final UUID uuid = UUID.randomUUID();

    @Column(nullable = false, unique = true)
    private String document;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    public Person(){}

    public Person(String document, String name, String email) {
        this.document = document;
        this.name = name;
        this.email = email;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
