package com.example.bookregistration.domain.author;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.*;
import org.hibernate.validator.constraints.Length;

import com.example.bookregistration.domain.author.dto.RequestAuthorDTO;
import com.example.bookregistration.domain.book.Book;
import com.example.bookregistration.domain.book.BookRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data
@Entity
@Table(name = "author")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
// @SQLDelete(sql = "UPDATE author SET active = false WHERE id = ?") //soft delete
@Where(clause = "active = true")
public class Author  {
    
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotBlank
    @NotNull
    @Length(min = 5, max = 30)
    @Column(length = 30, nullable = false) 
    private String name;

    @NotBlank
    @NotNull
    @Length(max = 30)
    // @Pattern(regexp = "")//the regular expression to match
    @Column(length = 30, nullable = false) 
    private String nationality;

    @NotNull
    @Column(nullable = false) 
    // @JsonIgnore //coluna não será enviada na resposta/ ignorada caso seja recebida
    private Boolean active = true; //Igualar o active a "true" é o mesmo o que usar o "this.active = true", o que está evidente na linha 55.
    
    // @OneToMany(cascade = CascadeType.ALL)
    // private List<Book> Books = new ArrayList<>();

    public Author(RequestAuthorDTO requestAuthor){
        this.name = requestAuthor.name();
        this.nationality = requestAuthor.nationality();
        this.active = true;
    }

}
