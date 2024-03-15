package com.example.bookregistration.domain.author.dto.mapper;

import org.springframework.stereotype.Component;

import com.example.bookregistration.domain.author.Author;
import com.example.bookregistration.domain.author.dto.RequestAuthorDTO;
import com.example.bookregistration.domain.author.dto.ResponseAuthorDTO;

@Component
public class AuthorMapper {

    public ResponseAuthorDTO toDTO(Author author){
        return new ResponseAuthorDTO(author.getId(),author.getName(),author.getNationality(),author.getActive());
    }

    //PAREI AQUI!!!! AULA 42 18 MINUTOS
    public Author toEntity(RequestAuthorDTO requestAuthorDTO){

        if(requestAuthorDTO == null){
            return null;
        }

        Author author = new Author();

        if(requestAuthorDTO.id() != null){
            author.setId(requestAuthorDTO.id());
        }

        author.setName(requestAuthorDTO.name());
        author.setNationality(requestAuthorDTO.nationality());

        return author;
    }
}
