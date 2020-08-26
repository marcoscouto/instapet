package com.github.marcoscouto.instapetzup.controllers;

import com.github.marcoscouto.instapetzup.dto.PostDTO;
import com.github.marcoscouto.instapetzup.models.Post;
import com.github.marcoscouto.instapetzup.services.impl.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Post> findAll(){
        return service.findAll();
    }

    @GetMapping("/author/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Post> findByAuthor(@PathVariable UUID id){
        return service.findByAuthor(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Post findById(@PathVariable UUID id){
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Post save(@Valid @RequestBody PostDTO post){
        return service.save(post);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Post update(@PathVariable UUID id, @Valid @RequestBody PostDTO post){
        return service.update(id, post);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable UUID id){
        service.delete(id);
    }


}
