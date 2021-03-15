package com.kodilla.library.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "TITLES")
public class Book {

    @Id
    @GeneratedValue
    @NonNull
    private Long id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "AUTHOR")
    private String author;

    @Column(name = "DATE_PUBLICATION")
    private Date datePublication;

    @OneToMany(
            targetEntity = BookCopy.class,
            mappedBy = "book",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<BookCopy> bookCopies;

    public Book(@NonNull Long id, String title, String author, Date datePublication) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.datePublication = datePublication;
    }
}
