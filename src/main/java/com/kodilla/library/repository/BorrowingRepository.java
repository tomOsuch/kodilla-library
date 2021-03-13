package com.kodilla.library.repository;

import com.kodilla.library.domain.Borrowing;
import org.springframework.data.repository.CrudRepository;
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
}
