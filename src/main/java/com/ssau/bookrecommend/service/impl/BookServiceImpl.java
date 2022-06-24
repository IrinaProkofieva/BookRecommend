package com.ssau.bookrecommend.service.impl;

import com.ssau.bookrecommend.model.Book;
import com.ssau.bookrecommend.repository.BookRepos;
import com.ssau.bookrecommend.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepos bookRepos;

    @Override
    public List<Book> getBooks(Integer page, Integer limit) {
        return bookRepos.findAll(PageRequest.of(page, limit));
    }

    @Override
    public Book getBook(Long id) {
        return bookRepos.findById(id).orElse(null);
    }

    @Override
    public List<Book> getBooksByName(String name, Integer page, Integer limit) {
        return bookRepos.findAllByName(name.toLowerCase(), PageRequest.of(page, limit));
    }
}
