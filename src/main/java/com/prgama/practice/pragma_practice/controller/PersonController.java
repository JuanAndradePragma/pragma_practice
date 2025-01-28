package com.prgama.practice.pragma_practice.controller;

import com.prgama.practice.pragma_practice.dto.PersonDto;
import com.prgama.practice.pragma_practice.dto.PersonRequest;
import com.prgama.practice.pragma_practice.service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("v1/persons")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/{id}")
    ResponseEntity<String> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body("Result ok for id: " + id); //ResponseEntity.ok().body(personService.findById(id));
    }

    @PostMapping
    ResponseEntity<String> save(@RequestBody PersonRequest personRequest) {
        return ResponseEntity.ok().body("Result ok for save"); //ResponseEntity.ok().body(personService.save(personRequest));
    }

}
