package com.ssau.bookrecommend.service;

import com.ssau.bookrecommend.model.Book;
import com.ssau.bookrecommend.model.Evaluation;

import java.util.List;

public interface RecommendationService {
    Evaluation evaluate(Long userId, Long bookId, Integer eval);
    List<Book> recommend(Long userId);
    List<Book> similar(Long bookId, Integer limit);
    Evaluation getEval(Long bookId, Long userId);
}
