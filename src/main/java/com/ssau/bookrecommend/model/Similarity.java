package com.ssau.bookrecommend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "similarity")
@IdClass(ComplexKeyBookBook.class)
public class Similarity {
    @Id
    @Column(name = "id_book_1")
    private Long idBook1;
    @Id
    @Column(name = "id_book_2")
    private Long idBook2;
    private double similarity;
}
