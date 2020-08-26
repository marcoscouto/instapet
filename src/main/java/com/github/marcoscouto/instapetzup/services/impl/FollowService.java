package com.github.marcoscouto.instapetzup.services.impl;

import com.github.marcoscouto.instapetzup.dto.FollowDTO;
import com.github.marcoscouto.instapetzup.exceptions.OperationNotAllowed;
import com.github.marcoscouto.instapetzup.models.Pet;
import com.github.marcoscouto.instapetzup.services.FollowServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FollowService implements FollowServiceInterface {

    private final PetService petService;

    @Override
    public List<Pet> findFollowers(UUID id) {
        return petService.findById(id).getFollowers();
    }

    @Override
    public List<Pet> findFollowing(UUID id) {
        return petService.findById(id).getFollowing();
    }

    @Transactional
    @Override
    public void follow(FollowDTO dto) {
        Pet follower = petService.findById(dto.getFollower());
        Pet following = petService.findById(dto.getFollowing());
        if(!follower.getFollowing().contains(following)){
            follower.getFollowing().add(following);
            petService.update(follower);
        } else throw new OperationNotAllowed("Operação não permitida, a relação já existe!");

    }

    @Transactional
    @Override
    public void unfollow(FollowDTO dto) {
        Pet follower = petService.findById(dto.getFollower());
        Pet following = petService.findById(dto.getFollowing());
        if(follower.getFollowing().contains(following)){
            follower.getFollowing().remove(following);
            petService.update(follower);
        } else throw new OperationNotAllowed("Operação não permitida, a relação não existe!");
    }
}
