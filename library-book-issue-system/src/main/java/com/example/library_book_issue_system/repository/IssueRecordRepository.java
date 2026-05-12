package com.example.library_book_issue_system.repository;

import com.example.library_book_issue_system.entity.IssueRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IssueRecordRepository extends JpaRepository<IssueRecord, Long> {
    
    List<IssueRecord> findByMemberMemberIdAndIsActiveTrue(Long memberId);
    
    Optional<IssueRecord> findByBookBookIdAndIsActiveTrue(Long bookId);
    
    List<IssueRecord> findByMemberMemberId(Long memberId);
}
