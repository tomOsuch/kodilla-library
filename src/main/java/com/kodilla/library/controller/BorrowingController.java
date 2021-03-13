package com.kodilla.library.controller;

import com.kodilla.library.dto.BorrowingDto;
import com.kodilla.library.mapper.BorrowingMapper;
import com.kodilla.library.services.BorrowingDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/getBorrowing/{id}")
    public BorrowingDto getBorrowing(@PathVariable Long id) {
        return borrowingMapper.mapToBorrowingDto(borrowingDbService.getBorrowing(id));
    }

    @GetMapping("/getBorrowings")
    public List<BorrowingDto> getAllBorrowing() {
        return borrowingMapper.mapToBorrowingDtoList(borrowingDbService.getAllBorrowing());
    }
}
