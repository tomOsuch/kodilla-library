package com.kodilla.library.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@NamedNativeQuery(
        name = "BookCopy.getAllAvailableBookCopies",
        query = "SELECT * FROM BOOK_COPIES " +
                "WHERE STATUS = 'AVAILABLE'",
        resultClass = BookCopy.class
)

@NamedNativeQuery(
        name = "BookCopy.getAllBookCopiesOfBook",
        query = "SELECT * FROM BOOK_COPIES " +
                "WHERE BOOK_ID = :BOOK_ID",
        resultClass = BookCopy.class
)

@NamedNativeQuery(
        name = "BookCopy.getAvailableBookCopiesOfBook",
        query = "SELECT * FROM BOOK_COPIES " +
                "WHERE (STATUS = 'AVAILABLE' AND BOOK_ID = :BOOK_ID)",
        resultClass = BookCopy.class
)

@NamedNativeQuery(
        name = "BookCopy.getAllBookCopiesOfTitle",
        query = "SELECT * FROM BOOK_COPIES BC " +
                "JOIN BOOKS B ON B.ID = BC.BOOK_ID " +
                "WHERE B.TITLE = :TITLE",
        resultClass = BookCopy.class
)

@NamedNativeQuery(
        name = "BookCopy.getAvailableBookCopiesOfTitle",
        query = "SELECT * FROM BOOK_COPIES BC " +
                "JOIN BOOKS B ON B.ID = BC.BOOK_ID " +
                "WHERE (BC.STATUS = 'AVAILABLE' AND B.TITLE = :TITLE)",
        resultClass = BookCopy.class
)

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "BOOKS")
public class BookCopy {

    @Id
    @GeneratedValue
    @NonNull
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private BookStatus status = BookStatus.AVAILABLE;

    @ManyToOne
    @JoinColumn(name = "TITLE_ID")
    private Book book;

    @OneToMany(
            targetEntity = Borrowing.class,
            mappedBy = "bookCopy",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Borrowing> borrowings;

    public BookCopy(Book book) {
        this.book = book;
    }

    public BookCopy(@NonNull Long id, BookStatus status, Book book) {
        this.id = id;
        this.status = status;
        this.book = book;
    }
}
