package com.github.marcoscouto.instapetzup.services;

import com.github.marcoscouto.instapetzup.dto.PermissionDTO;
import com.github.marcoscouto.instapetzup.dto.PetDTO;
import com.github.marcoscouto.instapetzup.models.Pet;
import com.github.marcoscouto.instapetzup.models.enums.Permission;

import java.util.List;
import java.util.UUID;

public interface PetServiceInterface {

    List<Pet> findAll();

    Pet findById(UUID id);

    Pet save(PetDTO pet);

    Pet update(UUID id, PetDTO pet);

    void changePetPermission(UUID id, PermissionDTO permission);

    void delete(UUID id);
}
