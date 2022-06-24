package com.ssau.bookrecommend.repository;

import com.ssau.bookrecommend.model.Book;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepos extends JpaRepository<Book,Long> {
    @Query(value = "select b from Book b")
    List<Book> findAll(PageRequest pageRequest);
    @Query(value = "select b from Book b where lower(b.name) like %?1%")
    List<Book> findAllByName(String name, PageRequest pageRequest);
}
