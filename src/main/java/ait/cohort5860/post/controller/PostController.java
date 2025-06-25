package ait.cohort5860.post.controller;

import ait.cohort5860.post.dto.NewCommentDto;
import ait.cohort5860.post.dto.NewPostDto;
import ait.cohort5860.post.dto.PostDto;
import ait.cohort5860.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/forum")
public class PostController {
    private final PostService postService;

    @ResponseStatus(HttpStatus.CREATED)
    public PostDto addNewPost(String author, NewPostDto newPostDto) {
        return null;
    }

    public PostDto findPostById(Long id) {
        return null;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addLike(Long id) {

    }

    public PostDto updatePost(Long id, NewPostDto newPostDto) {
        return null;
    }

    public PostDto deletePost(Long id) {
        return null;
    }

    public PostDto addComment(Long id, String author, NewCommentDto newCommentDto) {
        return null;
    }

    public Iterable<PostDto> findPostsByAuthor(String author) {
        return null;
    }

    public Iterable<PostDto> findPostsByTags(List<String> tags) {
        return null;
    }

    public Iterable<PostDto> findPostsByPeriod(LocalDate dateFrom, LocalDate dateTo) {
        return null;
    }
}
