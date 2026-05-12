package com.example.library_book_issue_system.service;

import com.example.library_book_issue_system.dto.MemberRequest;
import com.example.library_book_issue_system.entity.Member;
import com.example.library_book_issue_system.exception.BusinessRuleException;
import com.example.library_book_issue_system.exception.ResourceNotFoundException;
import com.example.library_book_issue_system.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    
    private final MemberRepository memberRepository;
    
    @Transactional
    public Member registerMember(MemberRequest request) {
        if (memberRepository.existsByEmail(request.getEmail())) {
            throw new BusinessRuleException("Member with email " + request.getEmail() + " already exists");
        }
        
        Member member = new Member();
        member.setName(request.getName());
        member.setEmail(request.getEmail());
        member.setActiveIssues(0);
        return memberRepository.save(member);
    }
    
    public Member getMemberById(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found with ID: " + memberId));
    }
    
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }
}
