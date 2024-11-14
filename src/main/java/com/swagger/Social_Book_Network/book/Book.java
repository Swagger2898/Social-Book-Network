package com.swagger.Social_Book_Network.book;

import com.swagger.Social_Book_Network.common.BaseEntity;
import com.swagger.Social_Book_Network.feedback.FeedBack;
import com.swagger.Social_Book_Network.history.BookTransactionHistory;
import com.swagger.Social_Book_Network.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Book extends BaseEntity {


    private String title;

    private String authorName;

    private String isbn;

    private String synopsis;

    private String bookCover;

    private boolean archived;

    private boolean shareable;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @OneToMany(mappedBy ="book")
    private List<FeedBack> feedBacks;

    @OneToMany(mappedBy ="book")
    private List<BookTransactionHistory> histories;

}
