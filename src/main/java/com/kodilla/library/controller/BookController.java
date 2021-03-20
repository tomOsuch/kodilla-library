package com.kodilla.library.controller;

import com.kodilla.library.domain.Book;
import com.kodilla.library.dto.BookDto;
import com.kodilla.library.exception.BookNotFoundException;
import com.kodilla.library.mapper.BookMapper;
import com.kodilla.library.services.BookDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/book")
public class BookController {

    private final BookDbService bookService;
    private final BookMapper bookMapper;

    @Autowired
    public BookController(BookDbService bookService, BookMapper bookMapper) {
        this.bookService = bookService;
        this.bookMapper = bookMapper;
    }

    @GetMapping("/getBooks")
    public List<BookDto> getAllBook() {
        List<Book> books = bookService.getAllBook();
        return bookMapper.mapToBookDtoList(books);
    }

    @GetMapping("/getBook/{id}")
    public BookDto getBook(@PathVariable Long id) {
        return bookMapper.mapToBookDto(bookService.getBook(id));
    }

    @PostMapping(value = "/createBook", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createBook(@RequestBody BookDto bookDto) {
        Book book = bookMapper.mapToBook(bookDto);
        bookService.saveBook(book);
    }

    @DeleteMapping("/deleteBook/{id}")
    public void deleteBook(@PathVariable Long id) {
        try {
            bookService.deleteBook(id);
        } catch (BookNotFoundException e) {
            throw new BookNotFoundException("Book with pointed ID does not exist");
        }
    }
}
