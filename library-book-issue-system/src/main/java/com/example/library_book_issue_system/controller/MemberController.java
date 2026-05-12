package com.example.library_book_issue_system.controller;

import com.example.library_book_issue_system.dto.ApiResponse;
import com.example.library_book_issue_system.dto.MemberRequest;
import com.example.library_book_issue_system.entity.IssueRecord;
import com.example.library_book_issue_system.entity.Member;
import com.example.library_book_issue_system.service.IssueService;
import com.example.library_book_issue_system.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
@Tag(name = "Member Management", description = "APIs for managing library members")
public class MemberController {
    
    private final MemberService memberService;
    private final IssueService issueService;
    
    @PostMapping
    @Operation(
        summary = "Register a new member",
        description = "Register a new library member with name and email"
    )
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "201",
            description = "Member registered successfully"
        ),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "400",
            description = "Invalid input or email already exists"
        )
    })
    public ResponseEntity<ApiResponse<Member>> registerMember(@Valid @RequestBody MemberRequest request) {
        Member member = memberService.registerMember(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success("Member registered successfully", member));
    }
    
    @GetMapping
    @Operation(
        summary = "Get all members",
        description = "Retrieve a complete list of all registered library members"
    )
    public ResponseEntity<ApiResponse<List<Member>>> getAllMembers() {
        List<Member> members = memberService.getAllMembers();
        return ResponseEntity.ok(ApiResponse.success("Members retrieved successfully", members));
    }
    
    @GetMapping("/{memberId}")
    @Operation(
        summary = "Get member by ID",
        description = "Retrieve detailed information about a specific member"
    )
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200",
            description = "Member found"
        ),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "404",
            description = "Member not found"
        )
    })
    public ResponseEntity<ApiResponse<Member>> getMemberById(
            @Parameter(description = "ID of the member to retrieve") @PathVariable Long memberId) {
        Member member = memberService.getMemberById(memberId);
        return ResponseEntity.ok(ApiResponse.success("Member retrieved successfully", member));
    }
    
    @GetMapping("/{memberId}/issues")
    @Operation(
        summary = "Get member's all issues",
        description = "Retrieve complete issue history for a member (both active and returned)"
    )
    public ResponseEntity<ApiResponse<List<IssueRecord>>> getMemberIssues(
            @Parameter(description = "ID of the member") @PathVariable Long memberId) {
        List<IssueRecord> issues = issueService.getMemberIssues(memberId);
        return ResponseEntity.ok(ApiResponse.success("Member issues retrieved successfully", issues));
    }
    
    @GetMapping("/{memberId}/active-issues")
    @Operation(
        summary = "Get member's active issues",
        description = "Retrieve only active (unreturned) book issues for a member"
    )
    public ResponseEntity<ApiResponse<List<IssueRecord>>> getActiveMemberIssues(
            @Parameter(description = "ID of the member") @PathVariable Long memberId) {
        List<IssueRecord> issues = issueService.getActiveMemberIssues(memberId);
        return ResponseEntity.ok(ApiResponse.success("Active member issues retrieved successfully", issues));
    }
}
