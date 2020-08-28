package com.github.marcoscouto.instapetzup.controllers;

import com.github.marcoscouto.instapetzup.dto.FollowDTO;
import com.github.marcoscouto.instapetzup.models.Pet;
import com.github.marcoscouto.instapetzup.services.impl.FollowService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Api(tags = "Follow")
@RequestMapping("/pets")
public class FollowController {

    private final FollowService followService;

    @GetMapping("/followers/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Pet> followers(@PathVariable UUID id){
        return followService.findFollowers(id);
    }

    @GetMapping("/following/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Pet> following(@PathVariable UUID id){
        return followService.findFollowing(id);
    }

    @PostMapping("/follow")
    @ResponseStatus(HttpStatus.OK)
    public void follow(@Valid @RequestBody FollowDTO dto){
        followService.follow(dto);
    }

    @PostMapping("/unfollow")
    @ResponseStatus(HttpStatus.OK)
    public void unfollow(@Valid @RequestBody FollowDTO dto){
        followService.unfollow(dto);
    }

}
