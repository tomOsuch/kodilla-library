package com.kodilla.library.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class BookDto {

    private final Long id;
    private final String title;
    private final String author;
    private final Date datePublication;
}
