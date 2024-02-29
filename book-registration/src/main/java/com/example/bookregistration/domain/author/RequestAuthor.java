package com.example.bookregistration.domain.author;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RequestAuthor(
    String id,
    @NotNull //@Length(max = 3)
    String name,
    @NotBlank
    String nationality
){


}
