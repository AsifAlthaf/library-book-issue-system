package com.example.library_book_issue_system.controller;

import com.example.library_book_issue_system.dto.ApiResponse;
import com.example.library_book_issue_system.dto.BookRequest;
import com.example.library_book_issue_system.entity.Book;
import com.example.library_book_issue_system.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
@Tag(name = "Book Management", description = "APIs for managing library books")
public class BookController {
    
    private final BookService bookService;
    
    @PostMapping
    @Operation(
        summary = "Add a new book",
        description = "Add a new book to the library inventory with title and author information"
    )
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "201",
            description = "Book added successfully",
            content = @Content(schema = @Schema(implementation = ApiResponse.class))
        ),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "400",
            description = "Invalid input - validation failed"
        )
    })
    public ResponseEntity<ApiResponse<Book>> addBook(@Valid @RequestBody BookRequest request) {
        Book book = bookService.addBook(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success("Book added successfully", book));
    }
    
    @GetMapping
    @Operation(
        summary = "Get all books",
        description = "Retrieve a complete list of all books in the library"
    )
    public ResponseEntity<ApiResponse<List<Book>>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return ResponseEntity.ok(ApiResponse.success("Books retrieved successfully", books));
    }
    
    @GetMapping("/available")
    @Operation(
        summary = "Get available books",
        description = "Retrieve a list of books that are currently available for issue"
    )
    public ResponseEntity<ApiResponse<List<Book>>> getAvailableBooks() {
        List<Book> books = bookService.getAvailableBooks();
        return ResponseEntity.ok(ApiResponse.success("Available books retrieved successfully", books));
    }
    
    @GetMapping("/{bookId}")
    @Operation(
        summary = "Get book by ID",
        description = "Retrieve detailed information about a specific book"
    )
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200",
            description = "Book found"
        ),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "404",
            description = "Book not found"
        )
    })
    public ResponseEntity<ApiResponse<Book>> getBookById(
            @Parameter(description = "ID of the book to retrieve") @PathVariable Long bookId) {
        Book book = bookService.getBookById(bookId);
        return ResponseEntity.ok(ApiResponse.success("Book retrieved successfully", book));
    }
    
    @GetMapping("/search/title")
    @Operation(
        summary = "Search books by title",
        description = "Search for books using partial title match (case-insensitive)"
    )
    public ResponseEntity<ApiResponse<List<Book>>> searchByTitle(
            @Parameter(description = "Title search term") @RequestParam String title) {
        List<Book> books = bookService.searchByTitle(title);
        return ResponseEntity.ok(ApiResponse.success("Books found", books));
    }
    
    @GetMapping("/search/author")
    @Operation(
        summary = "Search books by author",
        description = "Search for books using partial author name match (case-insensitive)"
    )
    public ResponseEntity<ApiResponse<List<Book>>> searchByAuthor(
            @Parameter(description = "Author search term") @RequestParam String author) {
        List<Book> books = bookService.searchByAuthor(author);
        return ResponseEntity.ok(ApiResponse.success("Books found", books));
    }
}
