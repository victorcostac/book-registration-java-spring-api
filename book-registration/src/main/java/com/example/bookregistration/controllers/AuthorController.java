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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.bookregistration.domain.author.Author;
import com.example.bookregistration.domain.author.AuthorRepositoy;
import com.example.bookregistration.domain.author.RequestAuthor;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/api/V1/author")
public class AuthorController {

    @Autowired
    private AuthorRepositoy authorRepository;

    @PostMapping("/register")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Author register(@RequestBody @Valid RequestAuthor data){

        Author author = new Author(data);
        return authorRepository.save(author);
    }

    @GetMapping("/findall")
    public ResponseEntity<List<Author>> findAll(){
        var allAuthor = authorRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(allAuthor);
    }

    @GetMapping("/findactive")
    public ResponseEntity<List<Author>> findAllActive(){
        var allactiveAuthors = authorRepository.findAllByActiveTrue();
        return ResponseEntity.status(HttpStatus.OK).body(allactiveAuthors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> findById(@PathVariable @NotNull String id){
        // Optional<Author> author = AuthorRepository.findById(id);

        // if(author.isPresent()){
        //     Author foundAuthor = author.get();
        //     return ResponseEntity.status(HttpStatus.OK).body(foundAuthor);    
        // }

        // return ResponseEntity.notFound().build();

        //OUTRA MANEIRA DE FAZER A BUSCA POR ID
        return authorRepository.findById(id)
        .map(record -> ResponseEntity.ok().body(record))// se encontra o dado retorna
        .orElse(ResponseEntity.notFound().build()); // se não encontra retorna nada 
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Author> update(@PathVariable @NotNull String id,
            @RequestBody @Valid RequestAuthor author){

        //FAZENDO O PUT DE FORMA NORMAL
        // return authorRepository.findById(id).map(recordFound -> {
        //     recordFound.setName(author.name());
        //     recordFound.setNationality(author.nationality());
        //     Author updated = authorRepository.save(recordFound);
        //     return ResponseEntity.status(HttpStatus.OK).body(updated);
        // })
        // .orElse(ResponseEntity.notFound().build());
        
        //USANDO O TRANSACTIONAL
        return authorRepository.findById(id).map(recordFound -> {
            recordFound.setName(author.name());
            recordFound.setNationality(author.nationality());
            // Author updated = authorRepository.save(recordFound);
            return ResponseEntity.status(HttpStatus.OK).body(recordFound);
        })
        .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable @NotNull String id){
        System.out.println("to aqui e aqui");
        return authorRepository.findById(id).map(recordFound -> {
            authorRepository.deleteById(recordFound.getId());
            return ResponseEntity.noContent().<Void>build();
            })
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/softdelete/{id}")
    @Transactional
    public ResponseEntity<Void> softDelete(@PathVariable @NotNull String id){

        return authorRepository.findById(id).map(recordFound -> {
            recordFound.setActive(false);
            return ResponseEntity.noContent().<Void>build();
            })
            .orElse(ResponseEntity.notFound().build());
    }

}
