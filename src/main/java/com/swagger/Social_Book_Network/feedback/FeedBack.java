package com.swagger.Social_Book_Network.feedback;

import com.swagger.Social_Book_Network.book.Book;
import com.swagger.Social_Book_Network.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class FeedBack extends BaseEntity {


    private Double note;

    private String comment;

    @ManyToOne
    @JoinColumn(name="book_id")
    private Book book ;



}
