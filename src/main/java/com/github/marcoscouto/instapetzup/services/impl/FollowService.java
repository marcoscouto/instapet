package com.github.marcoscouto.instapetzup.services.impl;

import com.github.marcoscouto.instapetzup.dto.FollowDTO;
import com.github.marcoscouto.instapetzup.exceptions.NotFoundException;
import com.github.marcoscouto.instapetzup.exceptions.OperationNotAllowed;
import com.github.marcoscouto.instapetzup.models.Follow;
import com.github.marcoscouto.instapetzup.models.Pet;
import com.github.marcoscouto.instapetzup.repositories.FollowRepository;
import com.github.marcoscouto.instapetzup.services.FollowServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FollowService implements FollowServiceInterface {

    private final FollowRepository followRepository;
    private final PetService petService;

    @Override
    public List<Pet> findFollowers(UUID id) {
        Pet pet = petService.findById(id);
        return followRepository.findByFollowing(pet).stream().map(Follow::getFollower).collect(Collectors.toList());
    }

    @Override
    public List<Pet> findFollowing(UUID id) {
        Pet pet = petService.findById(id);
        return followRepository.findByFollower(pet).stream().map(Follow::getFollowing).collect(Collectors.toList());
    }

    @Override
    public void follow(FollowDTO dto) {
        if(!findFollow(dto).isPresent()){
            followRepository.save(dtoToFollow(dto));
        } else throw new OperationNotAllowed("A relação já existe");
    }

    public Optional<Follow> findFollow(FollowDTO dto){
        Pet following = petService.findById(dto.getFollowing());
        Pet follower = petService.findById(dto.getFollower());
        return followRepository.findByFollowerAndFollowing(follower, following);
    }

    @Override
    public void unfollow(FollowDTO dto) {
        Optional<Follow> follow = findFollow(dto);
        if(follow.isPresent()){
            followRepository.delete(follow.get());
        } else throw new OperationNotAllowed("A relação não existe");
    }

    public Follow dtoToFollow(FollowDTO dto){
        Follow follow = new Follow();
        Pet following = petService.findById(dto.getFollowing());
        Pet follower = petService.findById(dto.getFollower());
        follow.setFollowing(following);
        follow.setFollower(follower);
        return follow;
    }
}
