package ait.cohort5860.post.dao;

import ait.cohort5860.post.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.stream.Stream;

public interface PostRepository extends JpaRepository<Post,Long> {
    Stream<Post> findByAuthor(String author);
    Stream<Post> findByTagsNameIn(Iterable<String> names);
    Stream<Post> findByDateCreatedBetween(LocalDateTime from, LocalDateTime to);

}

