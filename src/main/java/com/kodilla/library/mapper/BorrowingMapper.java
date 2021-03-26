package com.kodilla.library.mapper;

import com.kodilla.library.domain.BookCopy;
import com.kodilla.library.domain.Borrowing;
import com.kodilla.library.domain.Reader;
import com.kodilla.library.dto.BorrowingDto;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BorrowingMapper {

    public Borrowing mapToBorrowing(BorrowingDto borrowingDto, BookCopy bookCopy, Reader reader) {
        return new Borrowing(
                borrowingDto.getId(),
                bookCopy,
                reader
        );
    }

    public BorrowingDto mapToBorrowingDto(Borrowing borrowing) {
        return new BorrowingDto(
                borrowing.getId(),
                borrowing.getBookCopy().getId(),
                borrowing.getReader().getId(),
                borrowing.getBorrowDate(),
                borrowing.getReturnDate()
        );
    }

    public List<BorrowingDto> mapToBorrowingDtoList(List<Borrowing> borrowings) {
        return borrowings.stream()
                .map(this::mapToBorrowingDto)
                .collect(Collectors.toList());
    }
}
