package com.example.library_book_issue_system.service;

import com.example.library_book_issue_system.dto.IssueBookRequest;
import com.example.library_book_issue_system.entity.Book;
import com.example.library_book_issue_system.entity.IssueRecord;
import com.example.library_book_issue_system.entity.Member;
import com.example.library_book_issue_system.exception.BusinessRuleException;
import com.example.library_book_issue_system.exception.ResourceNotFoundException;
import com.example.library_book_issue_system.repository.IssueRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IssueService {
    
    private final IssueRecordRepository issueRecordRepository;
    private final BookService bookService;
    private final MemberService memberService;
    
    private static final int MAX_BOOKS_PER_MEMBER = 3;
    
    @Transactional
    public IssueRecord issueBook(IssueBookRequest request) {
        Book book = bookService.getBookById(request.getBookId());
        Member member = memberService.getMemberById(request.getMemberId());
        
        // Business Rule 1: Book must be available
        if (!book.getAvailability()) {
            throw new BusinessRuleException("Book is not available for issue");
        }
        
        // Business Rule 2: Member can have maximum 3 active issues
        if (member.getActiveIssues() >= MAX_BOOKS_PER_MEMBER) {
            throw new BusinessRuleException(
                    "Member has reached the maximum limit of " + MAX_BOOKS_PER_MEMBER + " active book issues"
            );
        }
        
        // Create issue record
        IssueRecord issueRecord = new IssueRecord();
        issueRecord.setBook(book);
        issueRecord.setMember(member);
        issueRecord.setIssueDate(LocalDate.now());
        issueRecord.setIsActive(true);
        
        // Update book availability
        book.setAvailability(false);
        
        // Update member active issues count
        member.setActiveIssues(member.getActiveIssues() + 1);
        
        return issueRecordRepository.save(issueRecord);
    }
    
    @Transactional
    public IssueRecord returnBook(Long issueId) {
        IssueRecord issueRecord = issueRecordRepository.findById(issueId)
                .orElseThrow(() -> new ResourceNotFoundException("Issue record not found with ID: " + issueId));
        
        if (!issueRecord.getIsActive()) {
            throw new BusinessRuleException("This book has already been returned");
        }
        
        // Update issue record
        issueRecord.setReturnDate(LocalDate.now());
        issueRecord.setIsActive(false);
        
        // Update book availability
        Book book = issueRecord.getBook();
        book.setAvailability(true);
        
        // Update member active issues count
        Member member = issueRecord.getMember();
        member.setActiveIssues(Math.max(0, member.getActiveIssues() - 1));
        
        return issueRecordRepository.save(issueRecord);
    }
    
    public List<IssueRecord> getMemberIssues(Long memberId) {
        memberService.getMemberById(memberId); // Validate member exists
        return issueRecordRepository.findByMemberMemberId(memberId);
    }
    
    public List<IssueRecord> getActiveMemberIssues(Long memberId) {
        memberService.getMemberById(memberId); // Validate member exists
        return issueRecordRepository.findByMemberMemberIdAndIsActiveTrue(memberId);
    }
}
