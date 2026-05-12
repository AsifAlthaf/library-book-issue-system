package com.example.library_book_issue_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "issue_records")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IssueRecord {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long issueId;
    
    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;
    
    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;
    
    @Column(nullable = false)
    private LocalDate issueDate;
    
    @Column
    private LocalDate returnDate;
    
    @Column(nullable = false)
    private Boolean isActive = true;
}
