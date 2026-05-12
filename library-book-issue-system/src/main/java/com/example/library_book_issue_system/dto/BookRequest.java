package com.example.library_book_issue_system.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Request object for adding a new book")
public class BookRequest {
    
    @NotBlank(message = "Title is required")
    @Schema(description = "Title of the book", example = "Clean Code", required = true)
    private String title;
    
    @NotBlank(message = "Author is required")
    @Schema(description = "Author of the book", example = "Robert C. Martin", required = true)
    private String author;
}
