package ait.cohort5860.post.service;

import ait.cohort5860.post.dao.PostRepository;
import ait.cohort5860.post.dao.TagRepository;
import ait.cohort5860.post.dto.NewCommentDto;
import ait.cohort5860.post.dto.NewPostDto;
import ait.cohort5860.post.dto.PostDto;
import ait.cohort5860.post.dto.exeption.PostNotFoundExeption;
import ait.cohort5860.post.model.Post;
import ait.cohort5860.post.model.Tag;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final TagRepository tagRepository;
    private final ModelMapper modelMapper;


    @Override
    @Transactional
    public PostDto addNewPost(String author, NewPostDto newPostDto) {
        Post post = new Post(newPostDto.getTitle(), newPostDto.getContent(), author);
        Set<String> tags = newPostDto.getTags();
        if (tags != null) {
            for (String tagName : tags) {
                Tag tag = tagRepository.findById(tagName).orElseGet(() -> tagRepository.save(new Tag(tagName)));
                post.addTags(tag);
            }
        }
        post =  postRepository.save(post);
        return modelMapper.map(post, PostDto.class);



    }

    @Override
    public PostDto findPostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(PostNotFoundExeption::new);
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public void addLike(Long id) {

    }

    @Override
    public PostDto updatePost(Long id, NewPostDto newPostDto) {
        return null;
    }

    @Override
    public PostDto deletePost(Long id) {
        return null;
    }

    @Override
    public PostDto addComment(Long id, String author, NewCommentDto newCommentDto) {
        return null;
    }

    @Override
    public Iterable<PostDto> findPostByAuthor(String author) {
        return null;
    }

    @Override
    public Iterable<PostDto> findPostByTags(List<String> tags) {
        return null;
    }

    @Override
    public Iterable<PostDto> findPostByPeriod(LocalDate dateFrom, LocalDate dateTo) {
        return null;
    }
}
