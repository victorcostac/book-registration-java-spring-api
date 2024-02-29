package com.example.bookregistration.domain.author;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface AuthorRepositoy extends JpaRepository<Author, String>{
    List<Author> findAllByActiveTrue();
}
