package com.kodilla.library.repository;

import com.kodilla.library.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void testBookRepositorySaveAndFindById() {
        //Given
        Book book = new Book("Title_Test", "Author_Test", new Date(1988));
        bookRepository.save(book);
        Long id = book.getId();
        //When
        Optional<Book> resultFindBook = bookRepository.findById(id);
        //Then
        assertTrue(resultFindBook.isPresent());
        //CleanUp
        bookRepository.deleteAll();
    }

    @Test
    public void testBookRepositoryFindAll() {
        //Given
        Book book1 = new Book("Title1", "Author1", new Date(1982));
        Book book2 = new Book("Title2", "Author2", new Date(1992));
        bookRepository.save(book1);
        bookRepository.save(book2);
        //When
        List<Book> bookList = bookRepository.findAll();
        //Then
        assertEquals(2,bookList.size());
    }

    @Test
    public void testBookRepositoryDeleteById() {
        //Given
        Book book = new Book("Title_Test", "Author_Test", new Date(1988));
        bookRepository.save(book);
        Long id = book.getId();
        //When
        bookRepository.deleteById(id);
        Optional<Book> resultFindBook = bookRepository.findById(id);
        //Then
        assertFalse(resultFindBook.isPresent());
    }
}
