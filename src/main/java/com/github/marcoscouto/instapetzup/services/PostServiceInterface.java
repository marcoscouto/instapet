package com.github.marcoscouto.instapetzup.services;

import com.github.marcoscouto.instapetzup.dto.PostDTO;
import com.github.marcoscouto.instapetzup.models.Post;

import java.util.List;
import java.util.UUID;

public interface PostServiceInterface {

    List<Post> findAll();

    List<Post> findByAuthor(UUID id);

    Post findById(UUID id);

    Post save(PostDTO post);

    Post update(UUID id, PostDTO post);

    void delete(UUID id);

    void like(UUID id);

    void dislike(UUID id);
}
