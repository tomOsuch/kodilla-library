package com.kodilla.library.services;

import com.kodilla.library.domain.Book;
import com.kodilla.library.domain.BookCopy;
import com.kodilla.library.domain.BookStatus;
import com.kodilla.library.exception.BookCopyNotFoundException;
import com.kodilla.library.exception.BookNotFoundException;
import com.kodilla.library.repository.BookCopyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookCopyDbService {

    private final BookCopyRepository bookCopyRepository;
    private final BookDbService bookService;

    @Autowired
    public BookCopyDbService(BookCopyRepository bookCopyRepository, BookDbService bookService) {
        this.bookCopyRepository = bookCopyRepository;
        this.bookService = bookService;
    }

    public List<BookCopy> getAllBookCopy() {
        return bookCopyRepository.findAll();
    }

    public BookCopy getBookCopy(Long id) {
        return bookCopyRepository.findById(id).orElseThrow(() -> new BookCopyNotFoundException("BookCopy with pointed ID does not exist"));
    }

    public List<BookCopy> getAllAvailableBookCopies() {
        return bookCopyRepository.getAllAvailableBookCopies();
    }

    public List<BookCopy> getAllBookCopiesOfBook(Long id) {
        return bookCopyRepository.getAllBookCopiesOfBook(id);
    }

    public List<BookCopy> getAllBookCopiesOfTitle(String title) {
        return bookCopyRepository.getAllBookCopiesOfTitle(title);
    }

    public List<BookCopy> getAvailableBookCopiesOfBook(Long id) {
        return bookCopyRepository.getAvailableBookCopiesOfBook(id);
    }

    public List<BookCopy> getAvailableBookCopiesOfTitle(String title) {
        return bookCopyRepository.getAvailableBookCopiesOfTitle(title);
    }

    public void saveBookCopy(Long id) throws BookNotFoundException {
        Book book = bookService.getBook(id);
        BookCopy bookCopy = new BookCopy(book);
        book.getBookCopies().add(bookCopy);
        bookCopyRepository.save(bookCopy);
    }

    public void deleteBookCopy(Long id) throws BookCopyNotFoundException, BookNotFoundException {
        BookCopy bookCopyToDelete = bookCopyRepository.findById(id).orElseThrow(() -> new BookCopyNotFoundException("BookCopy with pointed ID does not exist"));
        Book book = bookService.getBook(bookCopyToDelete.getBook().getId());
        book.getBookCopies().remove(bookCopyToDelete);
        bookService.saveBook(book);
    }

    public BookCopy setStatus(Long id, BookStatus status) throws BookCopyNotFoundException {
        BookCopy bookCopy = getBookCopy(id);
        bookCopy.setStatus(status);
        return bookCopyRepository.save(bookCopy);
    }


}
