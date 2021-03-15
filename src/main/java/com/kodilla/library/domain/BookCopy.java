package com.kodilla.library.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

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
