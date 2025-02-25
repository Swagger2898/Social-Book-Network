package com.swagger.Social_Book_Network.book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


public record BookRequest(
        Integer id,

        @NotBlank(message = "100")
        String title,

        @NotBlank(message = "101")
        String authorName,

        @NotBlank(message = "102")
        String isbn,

        @NotBlank(message = "103")
        String synopsis,

        boolean archived,

        boolean shareable) {
}
