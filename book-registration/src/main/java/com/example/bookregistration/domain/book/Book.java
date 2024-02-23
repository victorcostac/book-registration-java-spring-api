package com.example.bookregistration.domain.book;

import jakarta.persistence.*;
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

    private String title;

    private String author;
   
    private String year_of_publication;

    private Integer quantity;

    private Boolean active;

    public Book(RequestBook requestbook){
        this.title = requestbook.title();
        this.author = requestbook.author();
        this.year_of_publication = requestbook.year_of_publication();
        this.quantity = requestbook.quantity();
    }

}
