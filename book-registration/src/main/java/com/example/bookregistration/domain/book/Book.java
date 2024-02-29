package com.example.bookregistration.domain.book;

import com.example.bookregistration.domain.author.Author;

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

    @Column(nullable = false) //column Length 
    private String title;
    
    @Column
    private String year_of_publication;

    @Column
    private Integer quantity;

    @Column
    private Boolean active;

    @OneToOne
    @JoinColumn(name = "id_author", referencedColumnName = "id")
    private Author author;

    public Book(RequestBook requestbook){
        this.title = requestbook.title();
        // this.author = requestbook.author();
        this.year_of_publication = requestbook.year_of_publication();
        this.quantity = requestbook.quantity();
        this.active = true;
        this.author = requestbook.author();
    }

}
