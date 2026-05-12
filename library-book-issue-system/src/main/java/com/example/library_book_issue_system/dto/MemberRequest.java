package com.example.library_book_issue_system.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Request object for registering a new member")
public class MemberRequest {
    
    @NotBlank(message = "Name is required")
    @Schema(description = "Full name of the member", example = "John Doe", required = true)
    private String name;
    
    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    @Schema(description = "Email address of the member", example = "john.doe@example.com", required = true)
    private String email;
}
