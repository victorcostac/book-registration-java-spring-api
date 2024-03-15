package com.example.bookregistration.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bookregistration.domain.author.Author;
import com.example.bookregistration.domain.author.AuthorRepositoy;
import com.example.bookregistration.domain.author.dto.RequestAuthorDTO;
import com.example.bookregistration.domain.book.Book;
import com.example.bookregistration.domain.book.BookRepository;
import com.example.bookregistration.domain.book.RequestBook;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
@RestController
@RequestMapping("/api/V1/book")
public class BookController {
    
    @Autowired
    private BookRepository repository;

    @Autowired
    private AuthorRepositoy authorRepository;
    
    @GetMapping("/{id}")
    public ResponseEntity<Book> getbook(@PathVariable String id) {
    
        Optional<Book> optionalBook = repository.findById(id);
        if(optionalBook.isPresent()){
            Book book = optionalBook.get();
            return ResponseEntity.status(HttpStatus.OK).body(book);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    
    @GetMapping("/findall")
    public ResponseEntity<List<Book>> getBooks() {
        var allBooks = repository.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(allBooks);
    }

    @PostMapping("/register")
    public ResponseEntity registerBook(@RequestBody @Valid RequestBook data) {
        

        Book newbook = new Book(data);
        repository.save(newbook);
        return ResponseEntity.status(HttpStatus.CREATED).body(newbook);
    }

    @PostMapping("/registermany")
    public ResponseEntity<List<Book>> registerBooks(@RequestBody @Valid List<RequestBook> dataList) {
        List<Book> newBooks = new ArrayList<>();

        for (RequestBook data : dataList) {
            Book newBook = new Book(data);
            repository.save(newBook);
            newBooks.add(newBook);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(newBooks);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity updateBook(@PathVariable String id, @RequestBody @Valid RequestBook data) {
        Optional<Book> optionalBook = repository.findById(id);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            book.setTitle(data.title());
            // book.setAuthor(data.author());
            book.setYear_of_publication(data.year_of_publication());
            book.setQuantity(data.quantity());
            return ResponseEntity.status(HttpStatus.OK).body(book);
        }
        return ResponseEntity.noContent().build();
    }

    // FAZER UM MAP!!!
    @PutMapping("/list/map/")
    @Transactional
    public ResponseEntity<List<Book>> updateBookbyMap(@RequestBody @Valid List<RequestBook> dataList) {

        List<Book> updatedBooksList = new ArrayList<Book>();

        for(RequestBook item : dataList){

            Optional<Book> optionalUpdatedBook = repository.findById(item.id()).map(resource -> {
                resource.setTitle(item.title());
                // resource.setAuthor(item.author());
                resource.setYear_of_publication(item.year_of_publication());
                resource.setQuantity(item.quantity());
                return resource;
            });
            if(optionalUpdatedBook.isPresent()){
                Book updatedBook = optionalUpdatedBook.get();
                updatedBooksList.add(updatedBook);
            }            
        }
        return ResponseEntity.status(HttpStatus.OK).body(updatedBooksList);
        
    }
}
