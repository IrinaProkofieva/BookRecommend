package com.ssau.bookrecommend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "evaluation")
@IdClass(ComplexKeyUserBook.class)
public class Evaluation {
    @Id
    @Column(name = "user_id")
    private Long userId;
    @Id
    @Column(name = "book_id")
    private Long bookId;
    private Integer eval;
}
