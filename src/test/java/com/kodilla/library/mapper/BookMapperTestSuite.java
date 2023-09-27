package com.kodilla.library.mapper;

import com.kodilla.library.domain.Book;
import com.kodilla.library.dto.BookDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class BookMapperTestSuite {

    @Autowired
    private BookMapper bookMapper;

    @Test
    public void testBookMapToBookDtoList() {
        List<Book> books = List.of(new Book());

        List<BookDto> bookDtoList = bookMapper.mapToBookDtoList(books);
    }
}
