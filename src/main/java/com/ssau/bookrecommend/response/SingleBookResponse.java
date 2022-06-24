package com.ssau.bookrecommend.response;

import com.ssau.bookrecommend.model.Book;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SingleBookResponse {
    Book book;
}
