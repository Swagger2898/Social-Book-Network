package com.swagger.Social_Book_Network.feedback;


import com.swagger.Social_Book_Network.common.PageResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController@RequestMapping
@RequiredArgsConstructor
@Tag(name="FeedBack")
public class FeedBackController {

    private final FeedBackService service ;

    @PostMapping
    public ResponseEntity<Integer> saveFeedBack(
            @Valid @RequestBody FeedBackRequest request,
            Authentication connectedUser
    ){
        return ResponseEntity.ok(service.save(request,connectedUser));
    }


    @GetMapping("/book/{book-id}")
    public ResponseEntity<PageResponse<FeedBackResponse>> findAllFeedBackByBook(
            @PathVariable("book-id") Integer bookId,
    @RequestParam(name="page" , defaultValue ="0", required =false) int page,
    @RequestParam(name ="size" , defaultValue = "10" , required = false) int size,
    Authentication connectedUser){
        return ResponseEntity.ok(service.findAllFeedBackByBook(bookId, page , size,connectedUser));
    }
}
