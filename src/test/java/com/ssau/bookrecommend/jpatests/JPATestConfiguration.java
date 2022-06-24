package com.ssau.bookrecommend.jpatests;

import com.ssau.bookrecommend.service.BookService;
import com.ssau.bookrecommend.service.impl.BookServiceImpl;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class JPATestConfiguration {
    @Bean
    public BookService bookService(){
        return new BookServiceImpl();
    }
}
