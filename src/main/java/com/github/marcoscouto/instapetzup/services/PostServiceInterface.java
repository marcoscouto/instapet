package com.github.marcoscouto.instapetzup.services;

import com.github.marcoscouto.instapetzup.models.Pet;
import com.github.marcoscouto.instapetzup.models.Post;

import java.util.List;
import java.util.UUID;

public interface PostServiceInterface {

    List<Post> findAll();

    List<Post> findByAuthor(Pet pet);

    Post findById(UUID id);

    Post save(Post post);

    Post update(UUID id, Post post);

    void delete(UUID id);
}
