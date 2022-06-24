package com.ssau.bookrecommend.controller;

import com.ssau.bookrecommend.model.Book;
import com.ssau.bookrecommend.response.BookListResponse;
import com.ssau.bookrecommend.response.SingleBookResponse;
import com.ssau.bookrecommend.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;


@EnableCaching
@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @Cacheable("books")
    @Operation(summary = "Получить список книг из каталога. Запрос с пагинацией.")
    @GetMapping("/v1/books")
    public BookListResponse getBooks(@Parameter(description = "Параметр для поиска по названию.")
                                         @RequestParam(required = false) String name,
                                     @RequestParam(required = false) Integer page,
                                     @RequestParam @Min(1) @Max(1000) Integer limit){
        if (page==null)
            page = 0;
        List<Book> books;
        if (name==null)
            books = bookService.getBooks(page, limit);
        else
            books = bookService.getBooksByName(name, page, limit);
        return new BookListResponse(books, books.size(), books.size()<limit?null:page+1);
    }

    @Operation(summary = "Получить сведения о книге по id.")
    @GetMapping("/v1/books/{book_id}")
    public SingleBookResponse getBook(@PathVariable("book_id") Long id){
        return new SingleBookResponse(bookService.getBook(id));
    }

}
