package ait.cohort5860.post.dto;

import lombok.Singular;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class PostDto {
    private Long id;
    private String title;
    private String content;
    private String author;
    private LocalDateTime dateCreated;

    @Singular
    private Set<String> tags;
    private Integer likes;
    @Singular
    private List<String> comments;
}
