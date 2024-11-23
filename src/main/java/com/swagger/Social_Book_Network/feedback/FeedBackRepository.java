package com.swagger.Social_Book_Network.feedback;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FeedBackRepository extends JpaRepository<FeedBack,Integer> {

    @Query(""" 
            select feedback from FeedBack feedback where feedback.book.id = :bookId
            """)
    Page<FeedBack> findAllBooksById(Integer bookId, Pageable pageable);
}
