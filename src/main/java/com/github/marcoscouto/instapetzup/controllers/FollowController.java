package com.github.marcoscouto.instapetzup.controllers;

import com.github.marcoscouto.instapetzup.dto.FollowDTO;
import com.github.marcoscouto.instapetzup.models.Pet;
import com.github.marcoscouto.instapetzup.services.impl.FollowService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation("Listar seguidores")
    @GetMapping("/followers/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Pet> findFollowers(@PathVariable UUID id){
        return followService.findFollowers(id);
    }

    @ApiOperation("Listar seguindo")
    @GetMapping("/following/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Pet> findFollowing(@PathVariable UUID id){
        return followService.findFollowing(id);
    }

    @ApiOperation("Seguir")
    @PostMapping("/follow")
    @ResponseStatus(HttpStatus.OK)
    public void follow(@Valid @RequestBody FollowDTO dto){
        followService.follow(dto);
    }

    @ApiOperation("Deixar de Seguir")
    @PostMapping("/unfollow")
    @ResponseStatus(HttpStatus.OK)
    public void unfollow(@Valid @RequestBody FollowDTO dto){
        followService.unfollow(dto);
    }

}
