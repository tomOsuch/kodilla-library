package com.kodilla.library.repository;

import com.kodilla.library.domain.BookCopy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface BookCopyRepository extends CrudRepository<BookCopy, Long> {

    List<BookCopy> findAll();
    Optional<BookCopy> findById(Long id);
    BookCopy save(BookCopy bookCopy);
    void deleteById(Long id);

    @Query(nativeQuery = true)
    List<BookCopy> getAllAvailableBookCopies();

    @Query(nativeQuery = true)
    List<BookCopy> getAllBookCopiesOfBook(@Param("BOOK_ID") Long bookId);

    @Query(nativeQuery = true)
    List<BookCopy> getAvailableBookCopiesOfBook(@Param("BOOK_ID") Long bookId);

    @Query(nativeQuery = true)
    List<BookCopy> getAllBookCopiesOfTitle(@Param("TITLE") String title);

    @Query(nativeQuery = true)
    List<BookCopy> getAvailableBookCopiesOfTitle(@Param("TITLE") String title);
}
