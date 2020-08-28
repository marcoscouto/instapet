package com.github.marcoscouto.instapetzup.controllers;

import com.github.marcoscouto.instapetzup.dto.CommentDTO;
import com.github.marcoscouto.instapetzup.models.Comment;
import com.github.marcoscouto.instapetzup.services.impl.CommentService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Api(tags = "Comments")
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/post/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Comment> findByPost(@PathVariable UUID id){
        return commentService.findByPost(id);
    }

    @GetMapping("/author/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Comment> findByAuthor(@PathVariable UUID id){
        return commentService.findByAuthor(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Comment findById(@PathVariable UUID id){
        return commentService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Comment save(@Valid @RequestBody CommentDTO dto){
        return commentService.save(dto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Comment update(@PathVariable UUID id, @Valid @RequestBody CommentDTO dto){
        return commentService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    void delete(@PathVariable UUID id){
        commentService.delete(id);
    }

}
