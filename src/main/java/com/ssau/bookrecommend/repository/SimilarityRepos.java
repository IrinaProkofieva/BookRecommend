package com.ssau.bookrecommend.repository;

import com.ssau.bookrecommend.model.ComplexKeyBookBook;
import com.ssau.bookrecommend.model.Similarity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SimilarityRepos extends JpaRepository<Similarity, ComplexKeyBookBook> {
    @Query("select s.idBook2 from Similarity s where s.idBook1=?1 order by s.similarity desc")
    List<Long> findTopSimilar(Long bookId, PageRequest pageRequest);
}
