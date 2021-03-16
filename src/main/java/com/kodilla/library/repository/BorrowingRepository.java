package com.kodilla.library.repository;

import com.kodilla.library.domain.Borrowing;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface BorrowingRepository extends CrudRepository<Borrowing, Long> {

    List<Borrowing> findAll();

    Optional<Borrowing> findById(Long id);

    Borrowing save(Borrowing borrowing);

    @Query(nativeQuery = true)
    List<Borrowing> getAllBorrowingsOfBookCopy(@Param("BOOK_COPY_ID") Long bookCopyId);

    @Query(nativeQuery = true)
    List<Borrowing> getActiveBorrowingsOfBookCopy(@Param("BOOK_COPY_ID") Long bookCopyId);

    @Query(nativeQuery = true)
    List<Borrowing> getAllBorrowingsOfReader(@Param("READER_ID") Long readerId);

    @Query(nativeQuery = true)
    List<Borrowing> getActiveBorrowingsOfReader(@Param("READER_ID") Long readerId);
}
