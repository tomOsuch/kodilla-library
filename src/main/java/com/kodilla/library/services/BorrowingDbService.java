package com.kodilla.library.services;

import com.kodilla.library.domain.BookCopy;
import com.kodilla.library.domain.BookStatus;
import com.kodilla.library.domain.Borrowing;
import com.kodilla.library.domain.Reader;
import com.kodilla.library.dto.BorrowingDto;
import com.kodilla.library.exception.BookCopyNotAvailableException;
import com.kodilla.library.exception.BookCopyNotFoundException;
import com.kodilla.library.exception.BorrowingNotFoundException;
import com.kodilla.library.exception.ReaderNotFoundException;
import com.kodilla.library.mapper.BorrowingMapper;
import com.kodilla.library.repository.BorrowingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    public Borrowing addBorrowing(BorrowingDto borrowingDto) throws BookCopyNotFoundException, ReaderNotFoundException, BookCopyNotAvailableException {
        BookCopy bookCopy = bookCopyDbService.getBookCopy(borrowingDto.getBookId());
        Reader reader = readerDbService.getReader(borrowingDto.getReaderId());
        if (BookStatus.AVAILABLE.equals(bookCopy.getStatus())) {
            bookCopyDbService.setStatus(bookCopy.getId(), BookStatus.BORROWED);
            return borrowingRepository.save(borrowingMapper.mapToBorrowing(borrowingDto, bookCopy, reader));
        } else {
            throw new BookCopyNotAvailableException("Chosen book copy is not available");
        }
    }

    public Borrowing makeReturn(Long id) throws BorrowingNotFoundException, BookCopyNotFoundException {
        Borrowing borrowing = getBorrowing(id);
        if (borrowing.getReturnDate() == null) {
            bookCopyDbService.setStatus(borrowing.getBookCopy().getId(), BookStatus.AVAILABLE);
            borrowing.setReturnDate(new Date());
            return borrowingRepository.save(borrowing);
        } else {
            throw new BorrowingNotFoundException("Chosen borrowing is already finished");
        }
    }

    public Borrowing finishBorrowingAs(Long id, BookStatus status) throws  BorrowingNotFoundException, BookCopyNotFoundException {
        Borrowing borrowing = getBorrowing(id);
        if (borrowing.getReturnDate() == null) {
            bookCopyDbService.setStatus(borrowing.getBookCopy().getId(), status);
            borrowing.setReturnDate(new Date());
            return borrowingRepository.save(borrowing);
        } else {
            throw new BorrowingNotFoundException("Chosen borrowing is already finished");
        }
    }

    public Borrowing getBorrowing(Long id) throws BorrowingNotFoundException {
        return borrowingRepository.findById(id).orElseThrow(() -> new BorrowingNotFoundException("Borrowing with pointed ID does not exist"));
    }

    public List<Borrowing> getAllBorrowing() {
        return borrowingRepository.findAll();
    }

    
}
