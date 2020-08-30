package com.github.marcoscouto.instapetzup.controllers;

import com.github.marcoscouto.instapetzup.dto.PetDTO;
import com.github.marcoscouto.instapetzup.models.Pet;
import com.github.marcoscouto.instapetzup.services.impl.PetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation("Listar todos os Pets")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Pet> findAll(){
        return petService.findAll();
    }

    @ApiOperation("Encontrar pet por Id")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Pet findById(@PathVariable UUID id){
        return petService.findById(id);
    }

    @ApiOperation("Criar um Pet")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pet save(@Valid @RequestBody PetDTO pet){
        return petService.save(pet);
    }

    @ApiOperation("Atualizar um Pet")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Pet update(@PathVariable UUID id, @Valid @RequestBody PetDTO pet){
        return petService.update(id, pet);
    }

    @ApiOperation("Deletar um Pet")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable UUID id){
        petService.delete(id);
    }

}
