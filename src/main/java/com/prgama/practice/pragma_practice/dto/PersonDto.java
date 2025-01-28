package com.prgama.practice.pragma_practice.dto;

import java.util.UUID;

public record PersonDto(
        UUID uuid,
        String name,
        String document,
        String email
){}