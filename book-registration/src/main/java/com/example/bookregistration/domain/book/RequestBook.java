package com.example.bookregistration.domain.book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RequestBook(
        String id,
        @NotBlank @NotNull
        String title,
        @NotBlank
        String author,
        @NotBlank
        String year_of_publication,
        Integer quantity){

        }
