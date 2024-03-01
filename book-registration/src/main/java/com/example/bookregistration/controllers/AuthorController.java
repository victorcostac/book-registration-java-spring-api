package com.example.bookregistration.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.bookregistration.domain.author.Author;
import com.example.bookregistration.domain.author.RequestAuthor;
import com.example.bookregistration.service.AuthorService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/api/V1/author")
public class AuthorController {

    
    private final AuthorService authorService; 

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/list")
    public @ResponseBody List<Author> list() {
        return authorService.list();
    }

    @GetMapping("/{id}")
    public Author findById(@PathVariable @NotNull String id) {
        return authorService.findById(id); //-----------> 01/03
    }

    @PostMapping("/create")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Author create(@RequestBody @Valid RequestAuthor data) {
        Author author = new Author(data);
        return authorService.create(author);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Author> update(@PathVariable @NotNull String id,
            @RequestBody @Valid RequestAuthor author) {
        return authorService.update(id, author)
                .map(recordFound -> ResponseEntity.status(HttpStatus.OK).body(recordFound))
                .orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable @NotNull String id) {
        if(authorService.delete(id)){
            return ResponseEntity.noContent().<Void>build();
        }
        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/softdelete/{id}")
    public ResponseEntity<Void> softDelete(@PathVariable @NotNull String id) {

        if(authorService.softDelete(id)){
            return ResponseEntity.noContent().<Void>build();
        }
        return ResponseEntity.notFound().build();
    }
       
    // @GetMapping("/findactive")
    // public ResponseEntity<List<Author>> findAllActive(){
    // var allactiveAuthors = authorRepository.findAllByActiveTrue();
    // return ResponseEntity.status(HttpStatus.OK).body(allactiveAuthors);
    // }

}
