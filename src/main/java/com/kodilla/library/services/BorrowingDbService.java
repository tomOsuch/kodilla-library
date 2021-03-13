package com.kodilla.library.services;

import com.kodilla.library.domain.Borrowing;
import com.kodilla.library.exception.BorrowingNotFoundException;
import com.kodilla.library.mapper.BorrowingMapper;
import com.kodilla.library.repository.BorrowingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BorrowingDbService {

    private  final BorrowingRepository borrowingRepository;
    private final BorrowingMapper borrowingMapper;
    private final BookCopyDbService bookCopyDbService;
    private final ReaderDbService readerDbService;

    @Autowired
    public BorrowingDbService(BorrowingRepository borrowingRepository, BorrowingMapper borrowingMapper, BookCopyDbService bookCopyDbService, ReaderDbService readerDbService) {
        this.borrowingRepository = borrowingRepository;
        this.borrowingMapper = borrowingMapper;
        this.bookCopyDbService = bookCopyDbService;
        this.readerDbService = readerDbService;
    }

    public Borrowing getBorrowing(Long id) throws BorrowingNotFoundException {
        return borrowingRepository.findById(id).orElseThrow(() -> new BorrowingNotFoundException("Borrowing with pointed ID does not exist"));
    }

    public List<Borrowing> getAllBorrowing() {
        return borrowingRepository.findAll();
    }

    
}
