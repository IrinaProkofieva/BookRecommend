package com.ssau.bookrecommend.repository;

import com.ssau.bookrecommend.model.ComplexKeyUserBook;
import com.ssau.bookrecommend.model.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvaluationRepos extends JpaRepository<Evaluation, ComplexKeyUserBook> {
    @Query("select e from Evaluation e where e.userId=?1 and e.eval>=?2")
    List<Evaluation> findAllByUserIdAndEval(Long userId, Integer eval);
    @Query("select e.bookId from Evaluation e where e.userId=?1")
    List<Long> findEvaluated(Long userId);
    Evaluation getEvaluationByBookIdAndUserId(Long bookId, Long userId);
}
