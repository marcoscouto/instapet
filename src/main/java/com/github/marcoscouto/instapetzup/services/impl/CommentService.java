package com.github.marcoscouto.instapetzup.services.impl;

import com.github.marcoscouto.instapetzup.dto.CommentDTO;
import com.github.marcoscouto.instapetzup.exceptions.NotFoundException;
import com.github.marcoscouto.instapetzup.models.Comment;
import com.github.marcoscouto.instapetzup.models.Pet;
import com.github.marcoscouto.instapetzup.models.Post;
import com.github.marcoscouto.instapetzup.repositories.CommentRepository;
import com.github.marcoscouto.instapetzup.services.CommentServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommentService implements CommentServiceInterface {

    private final CommentRepository commentRepository;
    private final PostService postService;
    private final PetService petService;

    @Override
    public List<Comment> findByPost(UUID id) {
        Post post = postService.findById(id);
        return commentRepository.findByPost(post);
    }

    @Override
    public List<Comment> findByAuthor(UUID id) {
        Pet pet = petService.findById(id);
        return commentRepository.findByAuthor(pet);
    }

    @Override
    public Comment findById(UUID id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Comentário não encontrado - Id: " + id));
    }

    @Override
    public Comment save(CommentDTO dto) {
        Comment comment = dtoToComment(dto);
        return commentRepository.save(comment);
    }

    @Override
    public Comment update(UUID id, CommentDTO dto) {
        Comment comment = findById(id);
        return commentRepository.save(dtoToComment(comment, dto));
    }

    @Override
    public void delete(UUID id) {
        findById(id);
        commentRepository.deleteById(id);
    }

    public Comment dtoToComment(Comment comment, CommentDTO dto){
        Post post = postService.findById(dto.getPost());
        Pet pet = petService.findById(dto.getAuthor());
        comment.setAuthor(pet);
        comment.setPost(post);
        comment.setText(dto.getText());
        return comment;
    }

    public Comment dtoToComment(CommentDTO dto){
        Comment comment = new Comment();
        Post post = postService.findById(dto.getPost());
        Pet pet = petService.findById(dto.getAuthor());
        comment.setAuthor(pet);
        comment.setPost(post);
        comment.setText(dto.getText());
        return comment;
    }

}
