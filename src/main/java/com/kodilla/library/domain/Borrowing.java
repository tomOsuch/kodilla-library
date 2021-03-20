package com.kodilla.library.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@NamedNativeQuery(
        name = "Borrowing.getAllBorrowingsOfBookCopy",
        query = "SELECT * FROM BORROWINGS " +
                "WHERE BOOK_COPY_ID = :BOOK_COPY_ID",
        resultClass = Borrowing.class
)

@NamedNativeQuery(
        name = "Borrowing.getActiveBorrowingsOfBookCopy",
        query = "SELECT * FROM BORROWINGS " +
                "WHERE (RETURN_DATE IS NULL AND BOOK_COPY_ID = :BOOK_COPY_ID)",
        resultClass = Borrowing.class
)

@NamedNativeQuery(
        name = "Borrowing.getAllBorrowingsOfReader",
        query = "SELECT * FROM BORROWINGS " +
                "WHERE READER_ID = :READER_ID",
        resultClass = Borrowing.class
)

@NamedNativeQuery(
        name = "Borrowing.getActiveBorrowingsOfReader",
        query = "SELECT * FROM BORROWINGS " +
                "WHERE (RETURN_DATE IS NULL AND READER_ID = :READER_ID)",
        resultClass = Borrowing.class
)

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "BORROWINGS")
public class Borrowing {

    @Id
    @GeneratedValue
    @NonNull
    private Long id;

    @Column(name = "BORROW_DATE")
    private Date borrowDate = new Date();

    @Column(name = "RETURN_DATE")
    private Date returnDate;

    @ManyToOne
    @JoinColumn(name = "BOOK_COPY_ID")
    private BookCopy bookCopy;

    @ManyToOne
    @JoinColumn(name = "READER_ID")
    private Reader reader;

    public Borrowing(@NonNull Long id, BookCopy bookCopy, Reader reader) {
        this.id = id;
        this.bookCopy = bookCopy;
        this.reader = reader;
    }


    public Borrowing(BookCopy bookCopy, Reader reader) {
        this.bookCopy = bookCopy;
        this.reader = reader;
    }
}
