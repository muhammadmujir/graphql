package com.example.graphql.dao;

import com.example.graphql.model.Post;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

public class PostDao {

    private final List<Post> posts;

    public PostDao(List<Post> posts) {
        this.posts = posts;
    }

    public Mono<List<Post>> getRecentPosts(int count, int offset) {
        return Mono.fromSupplier(() -> posts.stream()
                .skip(offset)
                .limit(count)
                .collect(Collectors.toList()));
    }

    public Mono<List<Post>> getAuthorPosts(String author) {
        return Mono.fromSupplier(() -> posts.stream()
                .filter(post -> author.equals(post.getAuthorId()))
                .collect(Collectors.toList()));
    }

    public void savePost(Post post) {
        posts.add(post);
    }
}
