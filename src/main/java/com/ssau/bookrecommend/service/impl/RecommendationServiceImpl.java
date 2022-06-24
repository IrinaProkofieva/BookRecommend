package com.ssau.bookrecommend.service.impl;

import com.ssau.bookrecommend.model.Book;
import com.ssau.bookrecommend.model.Evaluation;
import com.ssau.bookrecommend.repository.BookRepos;
import com.ssau.bookrecommend.repository.EvaluationRepos;
import com.ssau.bookrecommend.repository.SimilarityRepos;
import com.ssau.bookrecommend.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class RecommendationServiceImpl implements RecommendationService {

    @Autowired
    private EvaluationRepos evaluationRepos;
    @Autowired
    private SimilarityRepos similarityRepos;
    @Autowired
    private BookRepos bookRepos;

    @Override
    public Evaluation evaluate(Long userId, Long bookId, Integer eval) {
        Evaluation evaluation = new Evaluation(userId, bookId, eval);
        return evaluationRepos.saveAndFlush(evaluation);
    }

    @Override
    public List<Book> recommend(Long userId) {
        int recSize = 48;
        int popSize = 2*recSize/3;
        List<Evaluation> likedBooks = evaluationRepos.findAllByUserIdAndEval(userId, 4);
        List<Long> readedBooks = evaluationRepos.findEvaluated(userId);
        HashMap<Long, Integer> recommendIds = new HashMap<>();
        if (likedBooks.size()<1)
            return new ArrayList<>();
        for(Evaluation ev: likedBooks){
            similarityRepos.findTopSimilar(ev.getBookId(), PageRequest.of(0,25)).forEach(x->recommendIds.compute(x,(k, v)->(v==null)?1:v+1));
        }
        Map<Boolean, List<Map.Entry<Long, Integer>>> multipleTimes = recommendIds.entrySet().stream().collect(Collectors.partitioningBy(x->x.getValue()>1));
        List<Long> recommendFirst = multipleTimes.get(true).stream().map(Map.Entry::getKey).filter(x->!readedBooks.contains(x)).collect(Collectors.toList());
        List<Long> recommendSecond = multipleTimes.get(false).stream().map(Map.Entry::getKey).filter(x->!readedBooks.contains(x)).collect(Collectors.toList());
        List<Long> recommendations;
        Collections.shuffle(recommendFirst);
        Collections.shuffle(recommendSecond);
        if(recommendFirst.size()>popSize){
            recommendations = recommendFirst.subList(0,popSize);
        }
        else {
            recommendations = recommendFirst;
        }
        recommendations.addAll(recommendSecond.subList(0,recSize-recommendations.size()));
        return recommendations.stream().map(x->bookRepos.findById(x).orElse(null)).collect(Collectors.toList());
    }

    @Override
    public List<Book> similar(Long bookId, Integer limit) {
        return similarityRepos.findTopSimilar(bookId, PageRequest.of(0,limit))
                .stream().map(x->bookRepos.findById(x).orElse(null)).collect(Collectors.toList());
    }

    @Override
    public Evaluation getEval(Long bookId, Long userId){
        return evaluationRepos.getEvaluationByBookIdAndUserId(bookId, userId);
    }
}
