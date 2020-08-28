package com.github.marcoscouto.instapetzup.controllers;

import com.github.marcoscouto.instapetzup.dto.PostDTO;
import com.github.marcoscouto.instapetzup.models.Post;
import com.github.marcoscouto.instapetzup.services.impl.PostService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Api(tags = "Posts")
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Post> findAll(){
        return postService.findAll();
    }

    @GetMapping("/author/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Post> findByAuthor(@PathVariable UUID id){
        return postService.findByAuthor(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Post findById(@PathVariable UUID id){
        return postService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Post save(@Valid @RequestBody PostDTO post){
        return postService.save(post);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Post update(@PathVariable UUID id, @Valid @RequestBody PostDTO post){
        return postService.update(id, post);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable UUID id){
        postService.delete(id);
    }

    @GetMapping("/{id}/like")
    @ResponseStatus(HttpStatus.OK)
    public void like(@PathVariable UUID id){
        postService.like(id);
    }

    @GetMapping("/{id}/dislike")
    @ResponseStatus(HttpStatus.OK)
    public void dislike(@PathVariable UUID id){
        postService.dislike(id);
    }


}
