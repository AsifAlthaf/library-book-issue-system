package com.example.library_book_issue_system.controller;

import com.example.library_book_issue_system.dto.ApiResponse;
import com.example.library_book_issue_system.dto.IssueBookRequest;
import com.example.library_book_issue_system.entity.IssueRecord;
import com.example.library_book_issue_system.service.IssueService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/issues")
@RequiredArgsConstructor
@Tag(name = "Issue & Return Operations", description = "APIs for issuing and returning books")
public class IssueController {
    
    private final IssueService issueService;
    
    @PostMapping("/issue")
    @Operation(
        summary = "Issue a book to a member",
        description = "Issue an available book to a registered member. Business rules: Book must be available, member can have maximum 3 active issues"
    )
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "201",
            description = "Book issued successfully"
        ),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "400",
            description = "Book not available or member has reached maximum limit"
        ),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "404",
            description = "Book or member not found"
        )
    })
    public ResponseEntity<ApiResponse<IssueRecord>> issueBook(@Valid @RequestBody IssueBookRequest request) {
        IssueRecord issueRecord = issueService.issueBook(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success("Book issued successfully", issueRecord));
    }
    
    @PutMapping("/return/{issueId}")
    @Operation(
        summary = "Return an issued book",
        description = "Process the return of an issued book. Updates return date and restores book availability"
    )
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200",
            description = "Book returned successfully"
        ),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "400",
            description = "Book already returned"
        ),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "404",
            description = "Issue record not found"
        )
    })
    public ResponseEntity<ApiResponse<IssueRecord>> returnBook(
            @Parameter(description = "ID of the issue record") @PathVariable Long issueId) {
        IssueRecord issueRecord = issueService.returnBook(issueId);
        return ResponseEntity.ok(ApiResponse.success("Book returned successfully", issueRecord));
    }
}
