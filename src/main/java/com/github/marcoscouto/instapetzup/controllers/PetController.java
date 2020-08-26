package com.github.marcoscouto.instapetzup.controllers;

import com.github.marcoscouto.instapetzup.models.Pet;
import com.github.marcoscouto.instapetzup.services.impl.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pets")
public class PetController {

    private final PetService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Pet> findAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Pet findById(@PathVariable UUID id){
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pet save(@RequestBody Pet pet){
        return service.save(pet);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Pet update(@PathVariable UUID id, @RequestBody Pet pet){
        return service.update(id, pet);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable UUID id){
        service.delete(id);
    }

}
