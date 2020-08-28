package com.github.marcoscouto.instapetzup.services.impl;

import com.github.marcoscouto.instapetzup.dto.PermissionDTO;
import com.github.marcoscouto.instapetzup.dto.PetDTO;
import com.github.marcoscouto.instapetzup.exceptions.NotFoundException;
import com.github.marcoscouto.instapetzup.models.Pet;
import com.github.marcoscouto.instapetzup.models.enums.Permission;
import com.github.marcoscouto.instapetzup.repositories.PetRepository;
import com.github.marcoscouto.instapetzup.services.PetServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PetService implements PetServiceInterface {

    private final PetRepository petRepository;
    private final BCryptPasswordEncoder encoder;

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
        return petRepository.save(createPetFromDto(dto));
    }

    @Override
    public Pet update(UUID id, PetDTO dto) {
        Pet pet = findById(id);
        return petRepository.save(updatePetFromDto(pet, dto));
    }

    @Override
    public void changePetPermission(UUID id, PermissionDTO permission) {
        Pet pet = findById(id);
        pet.setPermission(permission.getPermission());
        petRepository.save(pet);
    }

    @Override
    public void delete(UUID id) {
        findById(id);
        petRepository.deleteById(id);
    }

    public Pet createPetFromDto(PetDTO dto){
        Pet pet = new Pet();
        pet.setName(dto.getName());
        pet.setBirthdate(dto.getBirthdate());
        pet.setBreed(dto.getBreed());
        pet.setGender(dto.getGender());
        pet.setType(dto.getType());
        pet.setEmail(dto.getEmail());
        pet.setPassword(encoder.encode(dto.getPassword()));
        pet.setPermission(Permission.USER);
        return pet;
    }

    public Pet updatePetFromDto(Pet pet, PetDTO dto){
        pet.setName(dto.getName());
        pet.setBirthdate(dto.getBirthdate());
        pet.setBreed(dto.getBreed());
        pet.setGender(dto.getGender());
        pet.setType(dto.getType());
        pet.setEmail(dto.getEmail());
        if(dto.getPassword() != null)
            pet.setPassword(encoder.encode(dto.getPassword()));
        return pet;
    }


}
