package com.example.bookregistration.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.example.bookregistration.domain.author.AuthorRepositoy;
import com.example.bookregistration.domain.author.dto.RequestAuthorDTO;
import com.example.bookregistration.domain.author.dto.ResponseAuthorDTO;
import com.example.bookregistration.domain.author.dto.mapper.AuthorMapper;
import com.example.bookregistration.exception.RecordNotFoundException;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Validated
@Service
public class AuthorService {

    private final AuthorRepositoy authorRepository;
    private final AuthorMapper authorMapper;

    public AuthorService(AuthorRepositoy authorRepositoy, AuthorMapper authorMapper) {
        this.authorRepository = authorRepositoy;
        this.authorMapper = authorMapper;
    }

    public List<ResponseAuthorDTO> list() {
        return authorRepository.findAll()
        .stream()
        .map(authorMapper::toDTO)//.map(author -> authorMapper.toDTO(author))
        .collect(Collectors.toList());
    }

    public ResponseAuthorDTO findById(@NotNull String id) {
        return authorRepository.findById(id).map(authorMapper::toDTO)//.map(author -> authorMapper.toDTO(author))
        .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public ResponseAuthorDTO create(@Valid @NotNull RequestAuthorDTO author) {
        return authorMapper.toDTO(authorRepository.save(authorMapper.toEntity(author)));
    }

    // @Transactional
    public ResponseAuthorDTO update(@NotNull String id,@Valid @NotNull RequestAuthorDTO author) {
        return authorRepository.findById(id).map(recordFound -> {
            recordFound.setName(author.name());
            recordFound.setNationality(author.nationality());
            return authorMapper.toDTO(recordFound);// return recordFound 

        })// .map(AuthorMapper::toDTO)
        .orElseThrow(() -> new RecordNotFoundException(id));
       
    }

    public void delete(@NotNull String id) {
        authorRepository.delete(authorRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id)));
    }

    @Transactional
    public void softDelete(@NotNull String id) {
       authorRepository.findById(id).map(recordFound -> {
            recordFound.setActive(false);
            return recordFound;
        }).orElseThrow(() -> new RecordNotFoundException(id));
    }
}
