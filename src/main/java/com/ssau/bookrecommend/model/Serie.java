package com.ssau.bookrecommend.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "serie")
public class Serie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
