package com.swagger.Social_Book_Network.history;


import com.swagger.Social_Book_Network.book.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookTransactionHistoryRepo extends JpaRepository<BookTransactionHistory, Integer > {

    @Query("""
            select history from BookTransactionHistory history where history.user.id = :userId
            """
    )
    Page<BookTransactionHistory> findAllBorrowedBooks(Pageable pageable, Integer userId);

    @Query("""
            select history from BookTransactionHistory history where history.book.owner.id = :userId
            """)
    Page<BookTransactionHistory> findAllReturnedBooks(Pageable pageable, Integer userId);


    @Query("""
        select count(history) > 0 from BookTransactionHistory history
        where history.user.id = :userId 
        and history.book.id = :bookId 
        and history.returnApproved = false
        """)
    boolean isAlreadyBorrowedbyUser(Integer bookId, Integer userId);

    @Query("""
            select transaction from BookTransactionHistory transaction where transaction.book.owner.id =:userId and transaction.book.id = :bookId
            and transaction.returned = true and transaction.returnApproved = false
                """)
    Optional<BookTransactionHistory> findByBookIdAndUserId(Integer bookId, Integer userId);

    @Query("""
            select transaction from BookTransactionHistory transaction where transaction.user.id =:userId and transaction.book.id = :bookId
            and transaction.returned = false and transaction.returnApproved = false
                """)
    Optional<BookTransactionHistory> findByBookIdAndOwnerId(Integer bookId, Integer userId);
}
