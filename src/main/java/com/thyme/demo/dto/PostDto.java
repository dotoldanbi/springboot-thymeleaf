package com.thyme.demo.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
    private Long id;
    @NotEmpty(message = "Title must not be empty")
    private String title;
    private String url;
    @NotEmpty(message = "Content must not be empty")
    private String content;
    @NotEmpty(message = "Short description must not be empty") 
    private String shortDescription;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;

}
