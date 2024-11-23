package com.swagger.Social_Book_Network.feedback;

import com.swagger.Social_Book_Network.book.Book;

import java.util.Objects;

public class FeedBackMapper {
    public FeedBack toFeedBack(FeedBackRequest request) {

        return FeedBack.builder()
                .note(request.note())
                .comment(request.comment())
                .book(Book.builder().id(request.bookId())
                .archived(false)
                .shareable(false)
                .build()
                )
                .build();
    }

    public FeedBackResponse toFeedBackResponse(FeedBack feedBack, Integer id) {

        return FeedBackResponse.builder()
                .note(feedBack.getNote())
                .comment(feedBack.getComment())
                .ownFeedBack((Objects.equals(feedBack.getCreatedBy(),id)))
                .build();



    }
}
