package com.github.marcoscouto.instapetzup.services.impl;

import com.github.marcoscouto.instapetzup.dto.PetDTO;
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

    private final PetRepository petRepository;

    @Override
    public List<Pet> findAll() {
        return petRepository.findAll();
    }

    @Override
    public Pet findById(UUID id) {
        return petRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Pet não encontrado - Id: " + id));
    }

    @Override
    public Pet save(PetDTO dto) {
        return petRepository.save(dtoToPet(dto));
    }

    @Override
    public Pet update(UUID id, PetDTO dto) {
        Pet pet = findById(id);
        return petRepository.save(dtoToPet(pet, dto));
    }

    public Pet update(Pet pet) {
        return petRepository.save(pet);
    }

    @Override
    public void delete(UUID id) {
        findById(id);
        petRepository.deleteById(id);
    }

    public Pet dtoToPet(PetDTO dto){
        Pet pet = new Pet();
        pet.setName(dto.getName());
        pet.setBirthdate(dto.getBirthdate());
        pet.setBreed(dto.getBreed());
        pet.setGender(dto.getGender());
        pet.setType(dto.getType());
        return pet;
    }

    public Pet dtoToPet(Pet pet, PetDTO dto){
        pet.setName(dto.getName());
        pet.setBirthdate(dto.getBirthdate());
        pet.setBreed(dto.getBreed());
        pet.setGender(dto.getGender());
        pet.setType(dto.getType());
        return pet;
    }


}
