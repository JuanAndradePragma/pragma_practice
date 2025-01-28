package com.prgama.practice.pragma_practice.repository;

import com.prgama.practice.pragma_practice.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PersonRepository extends JpaRepository<Person, UUID> {
}
