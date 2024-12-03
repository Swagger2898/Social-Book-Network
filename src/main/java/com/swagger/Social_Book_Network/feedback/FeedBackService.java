package com.swagger.Social_Book_Network.feedback;

import com.swagger.Social_Book_Network.book.Book;
import com.swagger.Social_Book_Network.book.BookRepository;
import com.swagger.Social_Book_Network.common.PageResponse;
import com.swagger.Social_Book_Network.handler.OperationNotPermittedException;
import com.swagger.Social_Book_Network.user.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class FeedBackService {

    private final BookRepository bookRepository;
    private final FeedBackMapper feedBackMapper;
    private final FeedBackRepository feedBackRepository;
    public Integer save(FeedBackRequest request, Authentication connectedUser) {

        Book book = bookRepository.findById(request.bookId())
                .orElseThrow(() -> new EntityNotFoundException("No Book found with ID :: " + request.bookId()));

        if (book.isArchived() || !book.isShareable()) {
            throw new OperationNotPermittedException("You cannot give feedback for archived or non shareable book");
        }
        User user = ((User) connectedUser.getPrincipal());
        if (Objects.equals(book.getOwner().getId(), user.getId())) {
            throw new OperationNotPermittedException("You cannot give feedback to your own book");
        }
        FeedBack feedBack = feedBackMapper.toFeedBack(request);
        return feedBackRepository.save(feedBack).getId();
    }


    public PageResponse<FeedBackResponse> findAllFeedBackByBook(Integer bookId, int page, int size, Authentication connectedUser) {

        Pageable pageable = PageRequest.of(page,size);
        User user = ((User) connectedUser.getPrincipal());
        Page<FeedBack> feedBacks = feedBackRepository.findAllBooksById(bookId , pageable);
        List<FeedBackResponse> feedBackResponses = feedBacks.stream()
                .map(f -> feedBackMapper.toFeedBackResponse(f, user.getId()))
                .toList();

        return new PageResponse<>(
                feedBackResponses,
                feedBacks.getNumber(),
                feedBacks.getSize(),
                feedBacks.getTotalElements(),
                feedBacks.getTotalPages(),
                feedBacks.isFirst(),
                feedBacks.isLast()
        );
    }
}