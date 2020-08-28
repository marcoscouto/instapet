package com.github.marcoscouto.instapetzup.services;

import com.github.marcoscouto.instapetzup.dto.CommentDTO;
import com.github.marcoscouto.instapetzup.models.Comment;

import java.util.List;
import java.util.UUID;

public interface CommentServiceInterface {

    List<Comment> findByPost(UUID id);

    List<Comment> findByAuthor(UUID id);

    Comment findById(UUID id);

    Comment save(CommentDTO dto);

    Comment update(UUID id, CommentDTO dto);

    void delete(UUID id);
}
