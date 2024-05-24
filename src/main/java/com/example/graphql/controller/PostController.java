package com.example.graphql.controller;

import com.example.graphql.dao.AuthorDao;
import com.example.graphql.dao.PostDao;
import com.example.graphql.model.Author;
import com.example.graphql.model.Post;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@RestController
public class PostController {

    private final PostDao postDao;
    private final AuthorDao authorDao;

    public PostController(PostDao postDao, AuthorDao authorDao) {
        this.postDao = postDao;
        this.authorDao = authorDao;
    }

    @QueryMapping
    public Mono<List<Post>> recentPosts(@Argument int count, @Argument int offset) {
        return postDao.getRecentPosts(count, offset);
    }

    @SchemaMapping
    public Mono<Author> author(Post post) {
        return authorDao.getAuthor(post.getAuthorId());
    }

    @SchemaMapping(typeName="Post", field="first_author")
    public Mono<Author> getFirstAuthor(Post post) {
        return authorDao.getAuthor(post.getAuthorId());
    }

    @MutationMapping
    public Post createPost(@Argument String title, @Argument String text,
                           @Argument String category, @Argument String authorId) {
        Post post = new Post();
        post.setId(UUID.randomUUID().toString());
        post.setTitle(title);
        post.setText(text);
        post.setCategory(category);
        post.setAuthorId(authorId);
        postDao.savePost(post);

        return post;
    }

}
