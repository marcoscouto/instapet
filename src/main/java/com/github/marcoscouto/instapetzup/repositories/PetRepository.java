package com.github.marcoscouto.instapetzup.repositories;

import com.github.marcoscouto.instapetzup.models.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PetRepository extends JpaRepository<Pet, UUID> {
}
