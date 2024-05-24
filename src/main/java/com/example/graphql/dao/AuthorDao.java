package com.example.graphql.dao;

import com.example.graphql.model.Author;
import reactor.core.publisher.Mono;

import java.util.List;

public class AuthorDao {
    private final List<Author> authors;

    public AuthorDao(List<Author> authors) {
        this.authors = authors;
    }

    public Mono<Author> getAuthor(String id) {
        return Mono.fromSupplier(() -> authors.stream()
                .filter(author -> id.equals(author.getId()))
                .findFirst()
                .orElseThrow(RuntimeException::new));
    }
}
