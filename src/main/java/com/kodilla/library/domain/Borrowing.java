package com.kodilla.library.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

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
    @JoinColumn(name = "BOOK_ID")
    private BookCopy bookCopy;

    @ManyToOne
    @JoinColumn(name = "READER_ID")
    private Reader reader;
}
