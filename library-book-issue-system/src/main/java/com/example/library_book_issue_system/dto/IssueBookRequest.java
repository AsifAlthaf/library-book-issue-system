package com.example.library_book_issue_system.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Request object for issuing a book to a member")
public class IssueBookRequest {
    
    @NotNull(message = "Book ID is required")
    @Schema(description = "ID of the book to issue", example = "1", required = true)
    private Long bookId;
    
    @NotNull(message = "Member ID is required")
    @Schema(description = "ID of the member issuing the book", example = "1", required = true)
    private Long memberId;
}
