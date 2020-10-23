package com.lfg.spring.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    
    private String body;
    private Long userId;
    private Long postId;

}
