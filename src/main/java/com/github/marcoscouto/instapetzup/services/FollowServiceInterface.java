package com.github.marcoscouto.instapetzup.services;

import com.github.marcoscouto.instapetzup.dto.FollowDTO;
import com.github.marcoscouto.instapetzup.models.Pet;

import java.util.List;
import java.util.UUID;

public interface FollowServiceInterface {

    List<Pet> findFollowers(UUID id);

    List<Pet> findFollowing(UUID id);

    void follow(FollowDTO dto);

    void unfollow(FollowDTO dto);
}
