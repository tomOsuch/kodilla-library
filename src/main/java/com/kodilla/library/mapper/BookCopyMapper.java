package com.kodilla.library.mapper;

import com.kodilla.library.domain.Book;
import com.kodilla.library.domain.BookCopy;
import com.kodilla.library.dto.BookCopyDto;
import com.kodilla.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookCopyMapper {

    @Autowired
    private BookRepository bookRepository;

    public BookCopy mapToBookCopy(BookCopyDto bookCopyDto, Book book) {
        return new BookCopy(
                bookCopyDto.getId(),
                bookCopyDto.getStatus(),
                book
        );
    }

    public BookCopyDto mapToBookCopyDto(BookCopy bookCopy) {
        return new BookCopyDto(
                bookCopy.getId(),
                bookCopy.getBook().getId(),
                bookCopy.getStatus()
        );
    }

    public List<BookCopyDto> mapToBookCopyDtoList(List<BookCopy> bookCopyList) {
        return bookCopyList.stream()
                .map(this::mapToBookCopyDto)
                .collect(Collectors.toList());
    }
}
