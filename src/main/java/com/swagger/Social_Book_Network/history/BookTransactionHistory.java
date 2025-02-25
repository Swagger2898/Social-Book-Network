package com.swagger.Social_Book_Network.history;

import com.swagger.Social_Book_Network.book.Book;
import com.swagger.Social_Book_Network.common.BaseEntity;
import com.swagger.Social_Book_Network.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor

public class BookTransactionHistory extends BaseEntity {

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="book_id")
    private Book book;
    private boolean returned;

    private boolean returnApproved ;



}
