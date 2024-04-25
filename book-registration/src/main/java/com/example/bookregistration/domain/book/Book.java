package com.example.bookregistration.domain.book;

import com.example.bookregistration.domain.author.Author;
import com.example.bookregistration.dto.BookDTO.RequestBookDTO;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "book")
@Entity(name = "book")
@EqualsAndHashCode

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Book {
    
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(length = 100,nullable = false) 
    private String title;
    
    @Column(length = 30, nullable = false)
    private String year_of_publication;

    @Column
    private Integer quantity;

    @Column
    private Boolean active;

    @ManyToOne
    @JoinColumn(name = "id_author")
    private Author author;

    public Book(RequestBookDTO RequestBookDTO){
        this.title = RequestBookDTO.title();
        this.year_of_publication = RequestBookDTO.year_of_publication();
        this.quantity = RequestBookDTO.quantity();
        this.active = true;
        this.author = RequestBookDTO.author();
    }

}
