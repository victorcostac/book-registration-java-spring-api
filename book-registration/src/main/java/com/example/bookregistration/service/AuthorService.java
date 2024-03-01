package com.example.bookregistration.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.example.bookregistration.domain.author.Author;
import com.example.bookregistration.domain.author.AuthorRepositoy;
import com.example.bookregistration.domain.author.RequestAuthor;
import com.example.bookregistration.exception.RecordNotFoundException;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Validated
@Service
public class AuthorService {

    private final AuthorRepositoy authorRepository;

    public AuthorService(AuthorRepositoy authorRepositoy) {
        this.authorRepository = authorRepositoy;
    }

    public List<Author> list() {
        return authorRepository.findAll();
    }

    public Author findById(@NotNull String id) {
        return authorRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public Author create(@Valid Author author) {

        return authorRepository.save(author);
    }

    @Transactional
    public Optional<Author> update(@NotNull String id,@Valid RequestAuthor author) {
        return authorRepository.findById(id).map(recordFound -> {
            recordFound.setName(author.name());
            recordFound.setNationality(author.nationality());
            return recordFound;
        });
                
    }

    public boolean delete(@NotNull String id) {
        return authorRepository.findById(id).map(recordFound -> {
            authorRepository.deleteById(recordFound.getId());
            return true;
        }).orElse(false);
    }
    @Transactional
    public boolean softDelete(@NotNull String id) {
        return authorRepository.findById(id).map(recordFound -> {
            recordFound.setActive(false);
            return true;
        }).orElse(false);
    }

}
