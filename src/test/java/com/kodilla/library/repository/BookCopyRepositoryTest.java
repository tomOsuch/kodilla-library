package com.kodilla.library.repository;

import com.kodilla.library.domain.Book;
import com.kodilla.library.domain.BookCopy;
import com.kodilla.library.domain.BookStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class BookCopyRepositoryTest {

    @Autowired
    private BookCopyRepository bookCopyRepository;
    @Autowired
    private BookRepository bookRepository;

    @Test
    public void testBookCopySaveAndFindById() {
        //Given
        Book book = new Book("Title_Test", "Author_Test", new Date(1988));
        bookRepository.save(book);

        BookCopy bookCopy1 = new BookCopy(book);
        bookCopyRepository.save(bookCopy1);
        BookCopy bookCopy2 = new BookCopy(book);
        bookCopyRepository.save(bookCopy2);

        Long id1 = bookCopy1.getId();
        Long id2 = bookCopy2.getId();
        //When
        Optional<BookCopy> findBookCopy1 = bookCopyRepository.findById(id1);
        Optional<BookCopy> findBookCopy2 = bookCopyRepository.findById(id2);
        //Then
        assertTrue(findBookCopy1.isPresent());
        assertTrue(findBookCopy2.isPresent());
        //CleanUp
        bookRepository.deleteAll();
    }

    @Test
    public void testGetAllAndAvailableBookCopyOfBook() {
        //Given
        Book book = new Book("Title_Test", "Author_Test", new Date(1988));
        bookRepository.save(book);

        Long bookId = book.getId()
;
        BookCopy bookCopy1 = new BookCopy(book);
        bookCopyRepository.save(bookCopy1);
        BookCopy bookCopy2 = new BookCopy(book);
        bookCopy2.setStatus(BookStatus.LOST);
        bookCopyRepository.save(bookCopy2);
        //When
        List<BookCopy> bookCopies = bookCopyRepository.findAll();
        List<BookCopy> availableBookCopies = bookCopyRepository.getAvailableBookCopiesOfBook(bookId);
        //Then
        assertEquals(2, bookCopies.size());
        assertEquals(1, availableBookCopies.size());
    }

    @Test
    public void testGetAllAndAvailableBookCopyOfTitle() {
        //Given
        Book book1 = new Book("Title_Test", "Author_Test", new Date(1988));
        bookRepository.save(book1);
        Book book2 = new Book("Title_Test", "Author_Test", new Date(1990));
        bookRepository.save(book2);
        Book book3 = new Book("Title_Test3", "Author_Test", new Date(1990));
        bookRepository.save(book3);

        BookCopy book1Copy1 = new BookCopy(book1);
        bookCopyRepository.save(book1Copy1);
        BookCopy book1Copy2 = new BookCopy(book1);
        book1Copy2.setStatus(BookStatus.BORROWED);
        bookCopyRepository.save(book1Copy2);
        BookCopy book2Copy1 = new BookCopy(book2);
        bookCopyRepository.save(book2Copy1);
        BookCopy book3Copy1 = new BookCopy(book3);
        bookCopyRepository.save(book3Copy1);
        //When
        List<BookCopy> allBookCopiesOfTitle = bookCopyRepository.getAllBookCopiesOfTitle("Title_Test");
        List<BookCopy> availableBookCopiesOfTitle = bookCopyRepository.getAvailableBookCopiesOfTitle("Title_Test");
        //Then
        assertEquals(3, allBookCopiesOfTitle.size());
        assertEquals(2, availableBookCopiesOfTitle.size());
        //CleanUp
        bookRepository.deleteAll();
        bookCopyRepository.deleteAll();
    }
}
