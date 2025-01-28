package com.prgama.practice.pragma_practice.service;

import com.prgama.practice.pragma_practice.dto.PersonDto;
import com.prgama.practice.pragma_practice.dto.PersonRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface PersonService {

    Page<PersonDto> findAll(Pageable pageable);
    PersonDto findById(UUID uuid);
    PersonDto save(PersonRequest personRequest);
}
