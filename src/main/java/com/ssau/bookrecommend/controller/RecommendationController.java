package com.ssau.bookrecommend.controller;

import com.ssau.bookrecommend.model.Book;
import com.ssau.bookrecommend.model.Evaluation;
import com.ssau.bookrecommend.response.BookEvalResponse;
import com.ssau.bookrecommend.response.BookListResponse;
import com.ssau.bookrecommend.response.EvaluationResponse;
import com.ssau.bookrecommend.service.RecommendationService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
public class RecommendationController {

    @Autowired
    private RecommendationService recommendationService;

    @Operation(description = "Оценить книгу пользователем. Только для авторизованных пользователей.")
    @PostMapping("/v1/recommendations/evaluate")
    @ResponseStatus(code = HttpStatus.CREATED)
    public EvaluationResponse evaluate(
            @RequestParam Long bookId,
            @RequestParam Integer eval,
            @RequestParam Long userId) {
        log.info(String.format("Пользователь %d оценил книгу %d на %d", userId, bookId, eval));
        return new EvaluationResponse(recommendationService.evaluate(userId, bookId, eval));
    }

    @Operation(description = "Получить персональные рекомендации. Только для авторизованных пользователей.")
    @GetMapping("/v1/recommendations")
    public BookListResponse recommend(@RequestParam Long userId) {
        log.info(String.format("Запрос рекомендаций для пользователя %d", userId));
        List<Book> books = recommendationService.recommend(userId);
        return new BookListResponse(books, books.size(), null);
    }

    @Operation(description = "Получение списка книг, похожих на заданную.")
    @GetMapping("/v1/recommendations/similar")
    public BookListResponse findSimilar(@RequestParam Long bookId, @RequestParam Integer limit){
        List<Book> books = recommendationService.similar(bookId, limit);
        return new BookListResponse(books, books.size(), null);
    }

    @Operation(description = "Получение оценки пользователем книги")
    @GetMapping("/v1/recommendations/get_eval")
    public BookEvalResponse getEval(@RequestParam Long userId, @RequestParam Long bookId){
        Evaluation evaluation = recommendationService.getEval(bookId, userId);
        return new BookEvalResponse(evaluation==null?null:evaluation.getEval());
    }
}
