package com.kodilla.library.services;

import com.kodilla.library.domain.Book;
import com.kodilla.library.dto.BookDto;
import com.kodilla.library.exception.BookNotFoundException;
import com.kodilla.library.mapper.BookMapper;
import com.kodilla.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookDbService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Autowired
    public BookDbService(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    public List<Book> getAllBook() {
        return bookRepository.findAll();
    }

    public Book getBook(Long id) throws BookNotFoundException {
        return bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Book with pointed ID does not exist"));
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public Book updateBook(BookDto bookDto) throws BookNotFoundException {
        bookRepository.findById(bookDto.getId()).orElseThrow(() -> new BookNotFoundException("Book with pointed ID does not exist"));
        return bookRepository.save(bookMapper.mapToBook(bookDto));
    }

    public void deleteBook(Long id) {
        try {
            bookRepository.deleteById(id);
        } catch (BookNotFoundException e) {
            throw new BookNotFoundException("Book with pointed ID does not exist");
        }

    }
}
