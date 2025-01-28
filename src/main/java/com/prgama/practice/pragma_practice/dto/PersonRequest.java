package com.prgama.practice.pragma_practice.dto;

import jakarta.validation.constraints.NotEmpty;

public record PersonRequest(
      @NotEmpty
      String name,
      @NotEmpty
      String document,
      @NotEmpty
      String email
) {
}
