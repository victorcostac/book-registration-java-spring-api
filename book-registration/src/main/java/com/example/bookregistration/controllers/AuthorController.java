package com.example.bookregistration.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
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

import com.example.bookregistration.domain.author.dto.RequestAuthorDTO;
import com.example.bookregistration.domain.author.dto.ResponseAuthorDTO;
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
    public @ResponseBody List<ResponseAuthorDTO> list() {
        return authorService.list();
    }

    @GetMapping("/{id}")
    public ResponseAuthorDTO findById(@PathVariable @NotNull String id) {
        return authorService.findById(id); //-----------> 01/03
    }

    @PostMapping("/create")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseAuthorDTO create(@RequestBody @Valid RequestAuthorDTO data) {
        return authorService.create(data);
    }

    @PutMapping("/{id}")
    public ResponseAuthorDTO update(@PathVariable @NotNull String id,
            @RequestBody @Valid @NotNull RequestAuthorDTO author) {
        return authorService.update(id, author);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NotNull String id) {
        authorService.delete(id);
    }


    @DeleteMapping("/soft/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void softDelete(@PathVariable @NotNull String id) {
        authorService.softDelete(id);



    }
       
    // @GetMapping("/findactive")
    // public ResponseEntity<List<Author>> findAllActive(){
    // var allactiveAuthors = authorRepository.findAllByActiveTrue();
    // return ResponseEntity.status(HttpStatus.OK).body(allactiveAuthors);
    // }

}
