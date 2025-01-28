package com.prgama.practice.pragma_practice.service;

import com.prgama.practice.pragma_practice.dto.PersonDto;
import com.prgama.practice.pragma_practice.dto.PersonRequest;

public interface PersonService {

    PersonDto findById(Long id);
    PersonDto save(PersonRequest personRequest);
}
