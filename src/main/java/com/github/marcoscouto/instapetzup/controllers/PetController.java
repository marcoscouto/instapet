package com.github.marcoscouto.instapetzup.controllers;

import com.github.marcoscouto.instapetzup.dto.PetDTO;
import com.github.marcoscouto.instapetzup.models.Pet;
import com.github.marcoscouto.instapetzup.services.impl.PetService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Api(tags = "Pets")
@RequestMapping("/pets")
public class PetController {

    private final PetService petService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Pet> findAll(){
        return petService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Pet findById(@PathVariable UUID id){
        return petService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pet save(@Valid @RequestBody PetDTO pet){
        return petService.save(pet);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Pet update(@PathVariable UUID id, @Valid @RequestBody PetDTO pet){
        return petService.update(id, pet);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable UUID id){
        petService.delete(id);
    }

}
