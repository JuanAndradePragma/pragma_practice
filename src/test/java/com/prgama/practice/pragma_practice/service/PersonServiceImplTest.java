package com.prgama.practice.pragma_practice.service;

import com.prgama.practice.pragma_practice.dto.PersonDto;
import com.prgama.practice.pragma_practice.dto.PersonMapper;
import com.prgama.practice.pragma_practice.dto.PersonRequest;
import com.prgama.practice.pragma_practice.entity.Person;
import com.prgama.practice.pragma_practice.repository.PersonRepository;
import com.prgama.practice.pragma_practice.service.impl.PersonServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class PersonServiceImplTest {

    private PersonServiceImpl personServiceImpl;

    @Mock
    private PersonRepository personRepository;

    @Mock
    private PersonMapper personMapper;

    @BeforeEach
    void setUp(){
        this.personServiceImpl = new PersonServiceImpl(personMapper, personRepository);
    }

    @Test
    void findAll() {
        Mockito.when(personRepository.findAll(Mockito.<Pageable>any())).thenReturn(new PageImpl<>(List.of(new Person())));
        Mockito.when(personMapper.toDto(Mockito.any())).thenReturn(new PersonDto(UUID.randomUUID(), "test", "123232", "test@mail.com"));

        Page<PersonDto> persons = personServiceImpl.findAll(Pageable.unpaged());
        Assertions.assertFalse(persons.isEmpty());
    }

    @Test
    void findByIdNotFound() {
        Mockito.when(personRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        Assertions.assertThrows(ResponseStatusException.class, () -> personServiceImpl.findById(UUID.randomUUID()));
    }

    @Test
    void findById() {
        PersonDto personDto = new PersonDto(UUID.randomUUID(), "test", "123232", "test@mail.com");
        Mockito.when(personRepository.findById(Mockito.any())).thenReturn(Optional.of(new Person()));
        Mockito.when(personMapper.toDto(Mockito.any())).thenReturn(personDto);

        PersonDto personDb = personServiceImpl.findById(UUID.randomUUID());
        Assertions.assertEquals(personDto.name(), personDb.name());
    }

    @Test
    void save() {
        PersonDto personDto = new PersonDto(UUID.randomUUID(), "test", "123232", "test@mail.com");
        Mockito.when(personRepository.save(Mockito.any())).thenReturn(new Person());
        Mockito.when(personMapper.toDto(Mockito.any())).thenReturn(personDto);
        Mockito.when(personMapper.toEntity(Mockito.any())).thenReturn(new Person());

        PersonDto personDb = personServiceImpl.save(new PersonRequest("test", "123232", "test@mail.com"));
        Assertions.assertNotNull(personDb.name());
    }

    @Test
    void saveWithException() {
        Mockito.when(personMapper.toEntity(Mockito.any())).thenReturn(new Person());
        Mockito.when(personRepository.save(Mockito.any())).thenThrow(DataIntegrityViolationException.class);
        Assertions.assertThrows(ResponseStatusException.class, () -> personServiceImpl.save(new PersonRequest("test", "123232", "test@mail.com")));
    }

}
