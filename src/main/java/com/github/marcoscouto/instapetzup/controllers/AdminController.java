package com.github.marcoscouto.instapetzup.controllers;

import com.github.marcoscouto.instapetzup.dto.PermissionDTO;
import com.github.marcoscouto.instapetzup.services.impl.PetService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Api(tags = "Admin")
@RequestMapping("/admin")
public class AdminController {

    private final PetService petService;

    @PostMapping("/changepermission/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void changePetPermission(@PathVariable UUID id, @Valid @RequestBody PermissionDTO permission){
        petService.changePetPermission(id, permission);
    }
}
