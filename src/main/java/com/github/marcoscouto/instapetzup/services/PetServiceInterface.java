package com.github.marcoscouto.instapetzup.services;

import com.github.marcoscouto.instapetzup.models.Pet;

import java.util.List;
import java.util.UUID;

public interface PetServiceInterface {

    List<Pet> findAll();

    Pet findById(UUID id);

    Pet save(Pet pet);

    Pet update(UUID id, Pet pet);

    void delete(UUID id);
}
