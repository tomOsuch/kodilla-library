package com.kodilla.library.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "BOOKS")
public class Book {

    @Id
    @GeneratedValue
    @NonNull
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private BookStatus status = BookStatus.AVAILABLE;

    @ManyToOne
    @JoinColumn(name = "TITLE_ID")
    private Title title;

    @OneToMany(
            targetEntity = Borrowing.class,
            mappedBy = "book",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Borrowing> borrowings;
}
