package com.ssau.bookrecommend.response;

import com.ssau.bookrecommend.model.Book;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class BookListResponse {
    List<Book> books;
    Integer size;
    Integer next;
}
