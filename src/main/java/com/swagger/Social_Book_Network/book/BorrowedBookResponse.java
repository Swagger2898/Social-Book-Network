package com.swagger.Social_Book_Network.book;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BorrowedBookResponse {



    private Integer id;
    private String title;
    private String authorName;
    private String isbn;
    private double rate;
    private boolean archived;
    private boolean returned;
    private boolean returnApproved;

}
