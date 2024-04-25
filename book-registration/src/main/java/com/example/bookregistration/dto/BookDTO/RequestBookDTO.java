package com.example.bookregistration.dto.BookDTO;

import com.example.bookregistration.domain.author.Author;

import jakarta.validation.constraints.NotNull;

public record RequestBookDTO(
    String id,
    @NotNull
    String title,
    
    @NotNull
    String year_of_publication,

    Integer quantity,

    Author author

) {
    
}
