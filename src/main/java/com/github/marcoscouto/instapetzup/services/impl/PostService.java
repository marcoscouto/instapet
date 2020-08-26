package com.github.marcoscouto.instapetzup.services.impl;

import com.github.marcoscouto.instapetzup.exceptions.NotFoundException;
import com.github.marcoscouto.instapetzup.models.Pet;
import com.github.marcoscouto.instapetzup.models.Post;
import com.github.marcoscouto.instapetzup.repositories.PostRepository;
import com.github.marcoscouto.instapetzup.services.PostServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostService implements PostServiceInterface {

    private final PostRepository repository;

    @Override
    public List<Post> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Post> findByAuthor(Pet pet) {
        return repository.findByAuthor(pet);
    }

    @Override
    public Post findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Post não encontrado - Id: " + id));
    }

    @Override
    public Post save(Post post) {
        post.setId(null);
        return repository.save(post);
    }

    @Override
    public Post update(UUID id, Post post) {
        findById(id);
        post.setId(id);
        return repository.save(post);
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
