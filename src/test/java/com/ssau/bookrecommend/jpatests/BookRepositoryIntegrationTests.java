package com.ssau.bookrecommend.jpatests;

import com.ssau.bookrecommend.model.Book;
import com.ssau.bookrecommend.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(JPATestConfiguration.class)
public class BookRepositoryIntegrationTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BookService bookService;
    
    private static final String name1 = "Три мушкетера";
    private static final String name2 = "Три товарища";

    @BeforeEach
    public void setUp(){
        //given
        Book book1 = new Book();
        book1.setName(name1);
        Book book2 = new Book();
        book2.setName(name2);
        entityManager.persist(book1);
        entityManager.persistAndFlush(book2);
    }
    
    @Test
    public void findAll_thenReturnBooks(){
        //when
        List<Book> foundBooks = bookService.getBooks(0,10);

        //then
        assertThat(foundBooks.size()).isEqualTo(2);
    }

    @Test
    public void findByName_thenReturnBook(){
        //when
        List<Book> foundBooks = bookService.getBooksByName("мушк", 0, 10);

        //then
        assertThat(foundBooks.size()).isEqualTo(1);
    }

}
