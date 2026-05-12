package com.example.library_book_issue_system.config;

import com.example.library_book_issue_system.entity.Book;
import com.example.library_book_issue_system.entity.Member;
import com.example.library_book_issue_system.repository.BookRepository;
import com.example.library_book_issue_system.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;
    
    @Override
    public void run(String... args) {
        // Initialize sample books
        if (bookRepository.count() == 0) {
            bookRepository.save(new Book(null, "Clean Code", "Robert C. Martin", true));
            bookRepository.save(new Book(null, "Effective Java", "Joshua Bloch", true));
            bookRepository.save(new Book(null, "Design Patterns", "Gang of Four", true));
            bookRepository.save(new Book(null, "Head First Java", "Kathy Sierra", true));
            bookRepository.save(new Book(null, "Spring in Action", "Craig Walls", true));
            System.out.println("✓ Sample books initialized");
        }
        
        // Initialize sample members
        if (memberRepository.count() == 0) {
            memberRepository.save(new Member(null, "John Doe", "john.doe@example.com", 0));
            memberRepository.save(new Member(null, "Jane Smith", "jane.smith@example.com", 0));
            memberRepository.save(new Member(null, "Alice Johnson", "alice.johnson@example.com", 0));
            System.out.println("✓ Sample members initialized");
        }
    }
}
