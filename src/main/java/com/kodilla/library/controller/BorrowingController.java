package com.kodilla.library.controller;

import com.kodilla.library.domain.BookStatus;
import com.kodilla.library.dto.BorrowingDto;
import com.kodilla.library.exception.BookCopyNotAvailableException;
import com.kodilla.library.exception.BookCopyNotFoundException;
import com.kodilla.library.exception.BorrowingNotFoundException;
import com.kodilla.library.mapper.BorrowingMapper;
import com.kodilla.library.services.BorrowingDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@RestController
@RequestMapping("/v1/borrowing")
public class BorrowingController {

    private final BorrowingDbService borrowingDbService;
    private final BorrowingMapper borrowingMapper;

    @Autowired
    public BorrowingController(BorrowingDbService borrowingDbService, BorrowingMapper borrowingMapper) {
        this.borrowingDbService = borrowingDbService;
        this.borrowingMapper = borrowingMapper;
    }

    @PostMapping(value = "addBorrowing", consumes = MediaType.APPLICATION_JSON_VALUE)
    public BorrowingDto addBorrowing(@RequestBody BorrowingDto borrowingDto) throws BorrowingNotFoundException, BookCopyNotAvailableException {
        return borrowingMapper.mapToBorrowingDto(borrowingDbService.addBorrowing(borrowingDto));
    }

    @GetMapping("/getBorrowing/{id}")
    public BorrowingDto getBorrowing(@PathVariable Long id) {
        return borrowingMapper.mapToBorrowingDto(borrowingDbService.getBorrowing(id));
    }

    @GetMapping("/getBorrowings")
    public List<BorrowingDto> getAllBorrowing() {
        return borrowingMapper.mapToBorrowingDtoList(borrowingDbService.getAllBorrowing());
    }

    @PutMapping("/makeReturn/{id}")
    public BorrowingDto makeReturn(@PathVariable Long id) throws BorrowingNotFoundException, BookCopyNotFoundException {
        return borrowingMapper.mapToBorrowingDto(borrowingDbService.makeReturn(id));
    }

    @PutMapping("/finishBorrowingAs/{id}")
    public BorrowingDto finishBorrowingAs(@PathVariable Long id, BookStatus status) throws BorrowingNotFoundException, BookCopyNotFoundException {
        return borrowingMapper.mapToBorrowingDto(borrowingDbService.finishBorrowingAs(id, status));
    }

    @GetMapping(value = "getAllBorrowingsOfBookCopy/{bookCopyId}")
    public List<BorrowingDto> getAllBorrowingsOfBookCopy(@PathVariable Long bookCopyId){
        return borrowingMapper.mapToBorrowingDtoList(borrowingDbService.getAllBorrowingsOfBookCopy(bookCopyId));
    }

    @GetMapping(value = "getActiveBorrowingsOfBookCopy/{bookCopyId}")
    public List<BorrowingDto> getActiveBorrowingsOfBookCopy(@PathVariable Long bookCopyId){
        return borrowingMapper.mapToBorrowingDtoList(borrowingDbService.getActiveBorrowingsOfBookCopy(bookCopyId));
    }

    @GetMapping(value = "getAllBorrowingsOfReader/{readerId}")
    public List<BorrowingDto> getAllBorrowingsOfReader(@PathVariable Long readerId){
        return borrowingMapper.mapToBorrowingDtoList(borrowingDbService.getAllBorrowingsOfReader(readerId));
    }

    @GetMapping(value = "getActiveBorrowingsOfReader/{readerId}")
    public List<BorrowingDto> getActiveBorrowingsOfReader(@PathVariable Long readerId){
        return borrowingMapper.mapToBorrowingDtoList(borrowingDbService.getActiveBorrowingsOfReader(readerId));
    }
}
