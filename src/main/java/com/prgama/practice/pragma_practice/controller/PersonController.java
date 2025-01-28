package com.prgama.practice.pragma_practice.controller;

import com.prgama.practice.pragma_practice.dto.PersonDto;
import com.prgama.practice.pragma_practice.dto.PersonRequest;
import com.prgama.practice.pragma_practice.service.PersonService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@RestController
@RequestMapping("v1/persons")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    ResponseEntity<Page<PersonDto>> findAll(Pageable pageable) {
        return ResponseEntity.ok().body(personService.findAll(pageable));
    }

    @GetMapping("/{uuid}")
    ResponseEntity<PersonDto> findById(@PathVariable UUID uuid) {
        return ResponseEntity.ok().body(personService.findById(uuid));
    }

    @PostMapping
    ResponseEntity<PersonDto> save(@RequestBody PersonRequest personRequest) {
        return ResponseEntity.ok().body(personService.save(personRequest));
    }

}
