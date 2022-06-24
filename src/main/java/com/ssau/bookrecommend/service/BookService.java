package com.ssau.bookrecommend.service;

import com.ssau.bookrecommend.model.Book;

import java.util.List;

public interface BookService {
    List<Book> getBooks(Integer page, Integer limit);
    Book getBook(Long id);
    List<Book> getBooksByName(String name, Integer page, Integer limit);
}
