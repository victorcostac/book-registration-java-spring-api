package com.example.bookregistration.domain.author;


import org.hibernate.annotations.*;
import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
// @SQLDelete(sql = "UPDATE author SET active = false WHERE id = ?")
@Where(clause = "active = true")
public class Author {
    
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
    private Boolean active = true; //Igualar o active a "true" é o mesmo o que usar o "this.active = true", o que está evidente na linha 55.
    
    public Author(RequestAuthor requestAuthor){
        this.name = requestAuthor.name();
        this.nationality = requestAuthor.nationality();
        this.active = true;
    }

}
