package com.ssau.bookrecommend;

import com.ssau.bookrecommend.service.BookService;
import com.ssau.bookrecommend.service.RecommendationService;
import com.ssau.bookrecommend.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BookRecommendApplicationTests {

    @Autowired
    private UserService userService;
    @Autowired
    private BookService bookService;
    @Autowired
    private RecommendationService recommendationService;

    @Test
    void aucMetric() {
        //User testUser = userService.signUp("test", "test");
        //recommendationService.evaluate(testUser.getId(),21815L, (int) Math.round(bookService.getBook(21815L).getRating()));
        //recommendationService.evaluate(testUser.getId(),24585l, (int) Math.round(bookService.getBook(24585l).getRating()));

/*        User testUser = userService.signIn("test", "test");
        double meanAuc = 0;
        for (int k = 0; k<30; k++) {
            List<Book> books = recommendationService.recommend(testUser.getId());
            double p = Math.pow(0.1, 1. / 48);
            double auc = 0;
            double w = 0;
            for (int n = 1; n <= 48; n++) {
                w = Math.pow(p, n - 1) * (1 - p);
                double good = 0;
                for (int i = 0; i < n; i++) {
                    if (books.get(i).getRating() > 0)
                        good++;
                }
                auc += w * (good / n);
            }
            meanAuc+=auc;
            System.out.println(auc);
        }
        System.out.println("Mean AUC: "+meanAuc/30); */
    }

}
