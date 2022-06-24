package com.ssau.bookrecommend.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Immutable
@Table(name = "usedbooks")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String annotation;
    @Column(name = "character_count")
    private Integer characterCnt;
    private double rating;
    private String language;
    @Column(name = "written_dt")
    private Date writtenDate;
    @ManyToMany
    @JoinTable(name = "book_author",joinColumns=@JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "author_id"))
    private Set<Author> author;
    @ManyToMany
    @JoinTable(name = "book_tag",joinColumns=@JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tag;
    @ManyToMany
    @JoinTable(name = "book_genre",joinColumns=@JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Set<Genre> genre;
    @ManyToMany
    @JoinTable(name = "book_serie",joinColumns=@JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "serie_id"))
    private Set<Serie> serie;
}
