package com.github.marcoscouto.instapetzup.repositories;

import com.github.marcoscouto.instapetzup.models.Follow;
import com.github.marcoscouto.instapetzup.models.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FollowRepository extends JpaRepository<Follow, UUID> {

    List<Follow> findByFollower(Pet pet);

    List<Follow> findByFollowing(Pet pet);

    Optional<Follow> findByFollowerAndFollowing(Pet follower, Pet following);

}
