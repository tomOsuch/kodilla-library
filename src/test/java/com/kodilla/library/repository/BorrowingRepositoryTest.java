package com.kodilla.library.repository;

import com.kodilla.library.domain.Book;
import com.kodilla.library.domain.BookCopy;
import com.kodilla.library.domain.Borrowing;
import com.kodilla.library.domain.Reader;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class BorrowingRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookCopyRepository bookCopyRepository;

    @Autowired
    private ReaderRepository readerRepository;

    @Autowired
    private BorrowingRepository borrowingRepository;

    @Test
    public void testBorrowingSaveAndFindAll() {
        //Given
        Book book1 = new Book("Title_Test", "Author_Test", new Date(1992));
        bookRepository.save(book1);

        BookCopy book1Copy1 = new BookCopy(book1);
        bookCopyRepository.save(book1Copy1);

        Reader reader1 = new Reader("Jan", "Kowalski");
        readerRepository.save(reader1);

        Borrowing borrowing = new Borrowing(book1Copy1, reader1);
        borrowingRepository.save(borrowing);
        //When
        List<Borrowing> borrowings = borrowingRepository.findAll();
        //Then
        assertEquals(1, borrowings.size());
        //CleanUp
        bookRepository.deleteAll();
        bookCopyRepository.deleteAll();
        readerRepository.deleteAll();
        borrowingRepository.deleteAll();
    }

    @Test
    public void testBorrowingFindById() {
        //Given
        Book book1 = new Book("Title_Test", "Author_Test", new Date(1992));
        bookRepository.save(book1);

        BookCopy book1Copy1 = new BookCopy(book1);
        bookCopyRepository.save(book1Copy1);

        Reader reader1 = new Reader("Jan", "Kowalski");
        readerRepository.save(reader1);

        Borrowing borrowing = new Borrowing(book1Copy1, reader1);
        borrowingRepository.save(borrowing);
        Long borrowingId = borrowing.getId();
        //When
        Optional<Borrowing> resultFindById = borrowingRepository.findById(borrowingId);
        //Then
        assertTrue(resultFindById.isPresent());
        //CleanUp
        bookRepository.deleteAll();
        bookCopyRepository.deleteAll();
        readerRepository.deleteAll();
        borrowingRepository.deleteAll();
    }

    @Test
    public void getAllAndActiveBorrowingsOfReader() {
        //Given
        Book book1 = new Book("Title_Test", "Author_Test", new Date(1992));
        bookRepository.save(book1);

        BookCopy book1Copy1 = new BookCopy(book1);
        bookCopyRepository.save(book1Copy1);

        BookCopy book1Copy2 = new BookCopy(book1);
        bookCopyRepository.save(book1Copy2);

        BookCopy book1Copy3 = new BookCopy(book1);
        bookCopyRepository.save(book1Copy3);

        Reader reader1 = new Reader("Jan", "Kowalski");
        readerRepository.save(reader1);

        Borrowing borrowing1 = new Borrowing(book1Copy1, reader1);
        Borrowing borrowing2 = new Borrowing(book1Copy2, reader1);
        Borrowing borrowing3 = new Borrowing(book1Copy3, reader1);
        borrowingRepository.save(borrowing1);
        borrowingRepository.save(borrowing2);
        borrowing3.setReturnDate(new Date());
        borrowingRepository.save(borrowing3);
        Long readerId = reader1.getId();
        //When
        List<Borrowing> resultAllBorrowingsOfReader = borrowingRepository.getAllBorrowingsOfReader(readerId);
        List<Borrowing> resultReadBorrowings = borrowingRepository.getActiveBorrowingsOfReader(readerId);
        //Then
        assertEquals(3, resultAllBorrowingsOfReader.size());
        assertEquals(2, resultReadBorrowings.size());
        //CleanUp
        bookRepository.deleteAll();
        bookCopyRepository.deleteAll();
        readerRepository.deleteAll();
        borrowingRepository.deleteAll();

    }

    @Test
    public void testGetAllAndActiveBorrowingsOfBookCopy() {
        //Given
        Book book = new Book("title1", "author1", new Date(1992));
        bookRepository.save(book);

        BookCopy bookCopy = new BookCopy(book);
        bookCopyRepository.save(bookCopy);
        Long bookCopyId = bookCopy.getId();

        Reader reader1 = new Reader("Adam", "Kwiatek");
        Reader reader2 = new Reader("Jan", "Kowalski");
        readerRepository.save(reader1);
        readerRepository.save(reader2);

        Borrowing borrowing1 = new Borrowing(bookCopy, reader1);
        borrowing1.setReturnDate(new Date());
        Borrowing borrowing2 = new Borrowing(bookCopy, reader2);

        //When
        borrowingRepository.save(borrowing1);
        borrowingRepository.save(borrowing2);
        //Then
        List<Borrowing> allBorrowings = borrowingRepository.getAllBorrowingsOfBookCopy(bookCopyId);
        List<Borrowing> activeBorrowings = borrowingRepository.getActiveBorrowingsOfBookCopy(bookCopyId);
        assertEquals(2, allBorrowings.size());
        assertEquals(1, activeBorrowings.size());

        //CleanUp
        bookRepository.deleteAll();
        bookCopyRepository.deleteAll();
        readerRepository.deleteAll();
        borrowingRepository.deleteAll();
    }
}
