package com.kodilla.library.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class ReaderDto {

    private final Long id;
    private final String firstName;
    private final String lastName;
    private final Date createdDate;
}
