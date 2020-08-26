package com.github.marcoscouto.instapetzup.services.impl;

import com.github.marcoscouto.instapetzup.exceptions.NotFoundException;
import com.github.marcoscouto.instapetzup.models.Pet;
import com.github.marcoscouto.instapetzup.repositories.PetRepository;
import com.github.marcoscouto.instapetzup.services.PetServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PetService implements PetServiceInterface {

    private final PetRepository repository;

    @Override
    public List<Pet> findAll() {
        return repository.findAll();
    }

    @Override
    public Pet findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Pet não encontrado - Id: " + id));
    }

    @Override
    public Pet save(Pet pet) {
        pet.setId(null);
        return repository.save(pet);
    }

    @Override
    public Pet update(UUID id, Pet pet) {
        findById(id);
        pet.setId(id);
        return repository.save(pet);
    }

    @Override
    public void delete(UUID id) {
        findById(id);
        repository.deleteById(id);
    }


}
