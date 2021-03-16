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

    @PutMapping("/makeReturn")
    public BorrowingDto makeReturn(@RequestParam Long id) throws BorrowingNotFoundException, BookCopyNotFoundException {
        return borrowingMapper.mapToBorrowingDto(borrowingDbService.makeReturn(id));
    }

    @PutMapping("/finishBorrowingAs")
    public BorrowingDto finishBorrowingAs(@RequestParam Long id, BookStatus status) throws BorrowingNotFoundException, BookCopyNotFoundException {
        return borrowingMapper.mapToBorrowingDto(borrowingDbService.finishBorrowingAs(id, status));
    }

    @GetMapping(value = "getAllBorrowingsOfBookCopy")
    public List<BorrowingDto> getAllBorrowingsOfBookCopy(@RequestParam Long bookCopyId){
        return borrowingMapper.mapToBorrowingDtoList(borrowingDbService.getAllBorrowingsOfBookCopy(bookCopyId));
    }

    @GetMapping(value = "getActiveBorrowingsOfBookCopy")
    public List<BorrowingDto> getActiveBorrowingsOfBookCopy(@RequestParam Long bookCopyId){
        return borrowingMapper.mapToBorrowingDtoList(borrowingDbService.getActiveBorrowingsOfBookCopy(bookCopyId));
    }

    @GetMapping(value = "getAllBorrowingsOfReader")
    public List<BorrowingDto> getAllBorrowingsOfReader(@RequestParam Long readerId){
        return borrowingMapper.mapToBorrowingDtoList(borrowingDbService.getAllBorrowingsOfReader(readerId));
    }

    @GetMapping(value = "getActiveBorrowingsOfReader")
    public List<BorrowingDto> getActiveBorrowingsOfReader(@RequestParam Long readerId){
        return borrowingMapper.mapToBorrowingDtoList(borrowingDbService.getActiveBorrowingsOfReader(readerId));
    }
}
