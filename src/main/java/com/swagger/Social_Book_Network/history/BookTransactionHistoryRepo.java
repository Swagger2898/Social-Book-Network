package com.swagger.Social_Book_Network.history;


import com.swagger.Social_Book_Network.book.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookTransactionHistoryRepo extends JpaRepository<BookTransactionHistory, Integer > {

    @Query("""
            select history from BookTransactionHistory history where history.user.id = :userId
            """

    )
    Page<BookTransactionHistory> findAllBorrowedBooks(Pageable pageable, Integer userId);
}
