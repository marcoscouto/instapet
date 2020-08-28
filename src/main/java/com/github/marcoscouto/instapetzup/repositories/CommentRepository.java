package com.github.marcoscouto.instapetzup.repositories;

import com.github.marcoscouto.instapetzup.models.Comment;
import com.github.marcoscouto.instapetzup.models.Pet;
import com.github.marcoscouto.instapetzup.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CommentRepository extends JpaRepository<Comment, UUID> {

    List<Comment> findByAuthor(Pet pet);

    List<Comment> findByPost(Post post);

}
