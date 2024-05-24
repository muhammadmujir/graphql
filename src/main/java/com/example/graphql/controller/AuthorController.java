package com.example.graphql.controller;

import com.example.graphql.dao.PostDao;
import com.example.graphql.model.Author;
import com.example.graphql.model.Post;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
public class AuthorController {

    private final PostDao postDao;

    public AuthorController(PostDao postDao) {
        this.postDao = postDao;
    }

    @SchemaMapping
    public Mono<List<Post>> posts(Author author) {
        return postDao.getAuthorPosts(author.getId());
    }
}
