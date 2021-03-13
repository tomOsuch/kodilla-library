package com.kodilla.library.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class BorrowingDto {

    private final Long id;
    private final Long bookId;
    private final Long readerId;
    private final Date borrowDate;
    private final Date returnDate;
}
