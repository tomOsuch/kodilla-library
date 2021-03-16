package com.kodilla.library.controller;

import com.kodilla.library.domain.BookStatus;
import com.kodilla.library.dto.BookCopyDto;
import com.kodilla.library.exception.BookCopyNotFoundException;
import com.kodilla.library.exception.BookNotFoundException;
import com.kodilla.library.mapper.BookCopyMapper;
import com.kodilla.library.services.BookCopyDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/bookCopy")
public class BookCopyController {

    private final BookCopyDbService bookCopyDbService;
    private final BookCopyMapper bookCopyMapper;

    @Autowired
    public BookCopyController(BookCopyDbService bookCopyDbService, BookCopyMapper bookCopyMapper) {
        this.bookCopyDbService = bookCopyDbService;
        this.bookCopyMapper = bookCopyMapper;
    }

    @PostMapping("/createBookCopy")
    public void createBookCopy(@RequestParam Long id) {
        bookCopyDbService.saveBookCopy(id);
    }

    @PutMapping("/setStatus")
    public BookCopyDto setStatus(@RequestParam Long id, BookStatus status) throws BookCopyNotFoundException {
        return  bookCopyMapper.mapToBookCopyDto(bookCopyDbService.setStatus(id, status));
    }

    @GetMapping("/getAllBookCopy")
    public List<BookCopyDto> getAllBookCopy() {
        return bookCopyMapper.mapToBookCopyDtoList(bookCopyDbService.getAllBookCopy());
    }

    @GetMapping("/getBookCopy")
    public BookCopyDto getBookCopy(@RequestParam Long id) {
        return bookCopyMapper.mapToBookCopyDto(bookCopyDbService.getBookCopy(id));
    }

    @GetMapping(value = "getAllAvailableBookCopies")
    public List<BookCopyDto> getAllAvailableBookCopies() {
        return bookCopyMapper.mapToBookCopyDtoList(bookCopyDbService.getAllAvailableBookCopies());
    }

    @GetMapping(value = "getAllBookCopiesOfBook")
    public List<BookCopyDto> getAllBookCopiesOfBook(@RequestParam Long bookId) {
        return bookCopyMapper.mapToBookCopyDtoList(bookCopyDbService.getAllBookCopiesOfBook(bookId));
    }

    @GetMapping(value = "getAvailableBookCopiesOfBook")
    public List<BookCopyDto> getAvailableBookCopiesOfBook(@RequestParam Long bookId) {
        return bookCopyMapper.mapToBookCopyDtoList(bookCopyDbService.getAvailableBookCopiesOfBook(bookId));
    }

    @GetMapping(value = "getAllBookCopiesOfTitle")
    public List<BookCopyDto> getAllBookCopiesOfTitle(@RequestParam String title) {
        return bookCopyMapper.mapToBookCopyDtoList(bookCopyDbService.getAllBookCopiesOfTitle(title));
    }

    @GetMapping(value = "getAvailableBookCopiesOfTitle")
    public List<BookCopyDto> getAvailableBookCopiesOfTitle(@RequestParam String title) {
        return bookCopyMapper.mapToBookCopyDtoList(bookCopyDbService.getAvailableBookCopiesOfTitle(title));
    }

    @DeleteMapping("/deleteBookCopy")
    public void deleteBookCopy(@RequestParam Long id) throws BookNotFoundException {
        bookCopyDbService.deleteBookCopy(id);
    }
}
