package com.kodilla.library.repository;

import com.kodilla.library.domain.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

    List<Book> findAll();
    Optional<Book> findById(Long id);
    Book save(Book book);
    void deleteById(Long id);
}
