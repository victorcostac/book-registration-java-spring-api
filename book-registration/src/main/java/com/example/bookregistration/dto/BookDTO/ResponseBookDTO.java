package com.example.bookregistration.dto.BookDTO;

import com.example.bookregistration.domain.author.Author;

import jakarta.validation.constraints.NotNull;

public record ResponseBookDTO(
    String id,
    @NotNull
    String title,
    @NotNull
    String year_of_publication,
    @NotNull
    Integer quantity,
    @NotNull
    Boolean active,
    @NotNull
    Author author 
) {
    
}
