package com.kodilla.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BookDto {

    private final Long id;
    private final Long titleId;
    private final BookStatus status;
}
