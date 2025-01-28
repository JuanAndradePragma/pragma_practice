package com.prgama.practice.pragma_practice.service.impl;

import com.prgama.practice.pragma_practice.dto.PersonDto;
import com.prgama.practice.pragma_practice.dto.PersonMapper;
import com.prgama.practice.pragma_practice.dto.PersonRequest;
import com.prgama.practice.pragma_practice.entity.Person;
import com.prgama.practice.pragma_practice.repository.PersonRepository;
import com.prgama.practice.pragma_practice.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonMapper personMapper;
    private final PersonRepository personRepository;
    private final Logger logger = LoggerFactory.getLogger(PersonServiceImpl.class);

    public PersonServiceImpl(PersonMapper personMapper, PersonRepository personRepository) {
        this.personMapper = personMapper;
        this.personRepository = personRepository;
    }

    @Override
    public Page<PersonDto> findAll(Pageable pageable) {
        return personRepository.findAll(pageable).map(personMapper::toDto);
    }

    @Override
    public PersonDto findById(UUID uuid) {
        return personMapper.toDto(personRepository.findById(uuid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Person not found")));
    }

    @Override
    public PersonDto save(PersonRequest personRequest) {
        try {
            Person person = personMapper.toEntity(personRequest);
            return personMapper.toDto(personRepository.save(person));
        } catch (Exception e) {
            logger.error("Error during save person => " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }
}
