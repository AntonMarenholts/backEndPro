package ait.cohort5860.post.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class CommentDto {
    private String user;
    private String message;
    private LocalDateTime dateCreated;
    private Integer likes;
}
