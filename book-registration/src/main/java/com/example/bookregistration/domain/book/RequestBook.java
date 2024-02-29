package com.example.bookregistration.domain.book;

import com.example.bookregistration.domain.author.Author;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RequestBook(
        String id,

        @NotBlank @NotNull
        String title,

        @NotBlank
        String year_of_publication,

        Integer quantity,

        @NotNull 
        Author author
        
        ){
        }
