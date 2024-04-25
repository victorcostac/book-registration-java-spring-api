package com.example.bookregistration.dto.BookDTO.mapper;

import org.springframework.stereotype.Component;

import com.example.bookregistration.domain.book.Book;
import com.example.bookregistration.dto.BookDTO.RequestBookDTO;
import com.example.bookregistration.dto.BookDTO.ResponseBookDTO;


@Component
public class BookMapper {

    public ResponseBookDTO toDTO(Book book){
        return new ResponseBookDTO(book.getId(), book.getTitle(), book.getYear_of_publication(),book.getQuantity(),book.getActive(),book.getAuthor());
    }

    public Book toEntity(RequestBookDTO requestBookDTO){
        
        if(requestBookDTO == null){
            return null;
        }

        Book book = new Book();

        book.setTitle(requestBookDTO.title());
        book.setYear_of_publication(requestBookDTO.year_of_publication());
        book.setQuantity(requestBookDTO.quantity());
        book.setAuthor(requestBookDTO.author());

        return book;
    }
}
