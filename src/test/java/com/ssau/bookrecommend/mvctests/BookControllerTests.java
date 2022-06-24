package com.ssau.bookrecommend.mvctests;

import com.ssau.bookrecommend.controller.BookController;
import com.ssau.bookrecommend.model.Book;
import com.ssau.bookrecommend.service.BookService;
import com.ssau.bookrecommend.service.impl.MyUserDetailsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
@MockBean(MyUserDetailsService.class)
public class BookControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookService bookService;

    private final String name = "Три мушкетера";

    @Test
    public void givenBooks_whenGetBooks_thenReturnJsonArray() throws Exception {
        Book book = new Book();
        book.setName(name);

        given(bookService.getBooks(eq(0), anyInt())).willReturn(List.of(book));

        mvc.perform(get("/v1/books")
                .queryParam("limit", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.books",hasSize(1)))
                .andExpect(jsonPath("$.books.[0].name", is(name)));
    }

}
