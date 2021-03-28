package com.kodilla.library.dto;

import com.kodilla.library.domain.BookStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BookCopyStatusDto {

    private final Long id;
    private final BookStatus bookStatus;
}
