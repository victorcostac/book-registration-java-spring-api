package com.example.bookregistration.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bookregistration.domain.author.AuthorRepositoy;
import com.example.bookregistration.domain.book.Book;
import com.example.bookregistration.domain.book.BookRepository;
import com.example.bookregistration.dto.BookDTO.RequestBookDTO;
import com.example.bookregistration.dto.BookDTO.ResponseBookDTO;
import com.example.bookregistration.dto.BookDTO.mapper.BookMapper;
import com.example.bookregistration.exception.RecordNotFoundException;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class BookService {
    
   
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookService(BookRepository bookRepository, BookMapper bookMapper){
        this.bookMapper = bookMapper;  
        this.bookRepository = bookRepository;
    }
    
    
    
    public ResponseBookDTO getbook(String id) {
    
        return bookRepository.findById(id).map(bookMapper::toDTO)//.map(author -> authorMapper.toDTO(author))
        .orElseThrow(() -> new RecordNotFoundException(id));
    }
    
    public List<ResponseBookDTO> getBooks() {
        return bookRepository.findAll()
        .stream()
        .map(bookMapper::toDTO)//.map(author -> authorMapper.toDTO(author))
        .collect(Collectors.toList());
    }

    
    public ResponseBookDTO createBook(@Valid RequestBookDTO data) {
        return bookMapper.toDTO(bookRepository.save(bookMapper.toEntity(data)));
    }

    
    public List<ResponseBookDTO> registerBooks(@Valid List<RequestBookDTO> dataList) {
        List<ResponseBookDTO> newBooksDTO = new ArrayList<>();

        for (RequestBookDTO data : dataList) {
            Book newBook = new Book(data);
            bookRepository.save(newBook);
            newBooksDTO.add(bookMapper.toDTO(newBook));
        }

        return newBooksDTO;
    }


    @Transactional
    public ResponseBookDTO updateBook(String id,@Valid RequestBookDTO data) {
        return bookRepository.findById(id).map(recordFound -> {
            recordFound.setTitle(data.title());
            recordFound.setYear_of_publication(data.year_of_publication());
            return bookMapper.toDTO(recordFound);// return recordFound 

        })// .map(AuthorMapper::toDTO)
        .orElseThrow(() -> new RecordNotFoundException(id));
    }

    // FAZER UM MAP!!!
    @Transactional
    public List<ResponseBookDTO> updateBookbyMap(@Valid List<RequestBookDTO> dataList) {
        return dataList.stream()
            .map(item -> bookRepository.findById(item.id())
                .map(resource -> {
                    resource.setTitle(item.title());
                    resource.setYear_of_publication(item.year_of_publication());
                    resource.setQuantity(item.quantity());
                    return bookMapper.toDTO(resource);
                }))
            .filter(Optional::isPresent)
            .map(Optional::get)
            .collect(Collectors.toList());
        
    }
}
