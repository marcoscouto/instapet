package com.github.marcoscouto.instapetzup.services.impl;

import com.github.marcoscouto.instapetzup.dto.PostDTO;
import com.github.marcoscouto.instapetzup.exceptions.NotFoundException;
import com.github.marcoscouto.instapetzup.exceptions.OperationNotAllowed;
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

    private final PostRepository postRepository;
    private final PetService petService;

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public List<Post> findByAuthor(UUID id) {
        Pet pet = petService.findById(id);
        return postRepository.findByAuthor(pet);
    }

    @Override
    public Post findById(UUID id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Post não encontrado - Id: " + id));
    }

    @Override
    public Post save(PostDTO dto) {
        return postRepository.save(dtoToPost(dto));
    }

    @Override
    public Post update(UUID id, PostDTO dto) {
        Post post = findById(id);
        return postRepository.save(dtoToPost(post, dto));
    }

    @Override
    public void delete(UUID id) {
        postRepository.deleteById(id);
    }

    @Override
    public void like(UUID id) {
        Post post = findById(id);
        post.setLikes(post.getLikes() + 1);
        postRepository.save(post);
    }

    @Override
    public void dislike(UUID id) {
        Post post = findById(id);
        if(post.getLikes() == 0)
            throw new OperationNotAllowed("Operação não permitida, o número de likes não pode ser negativo");
        post.setLikes(post.getLikes() - 1);
        postRepository.save(post);
    }

    public Post dtoToPost(Post post, PostDTO dto){
        post.setTitle(dto.getTitle());
        post.setText(dto.getText());
        Pet author = petService.findById(dto.getAuthor());
        post.setAuthor(author);
        return post;
    }

    public Post dtoToPost(PostDTO dto){
        Post post = new Post();
        post.setTitle(dto.getTitle());
        post.setText(dto.getText());
        Pet author = petService.findById(dto.getAuthor());
        post.setAuthor(author);
        return post;
    }
}
