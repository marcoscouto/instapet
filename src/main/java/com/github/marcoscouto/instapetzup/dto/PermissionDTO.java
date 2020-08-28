package com.github.marcoscouto.instapetzup.dto;

import com.github.marcoscouto.instapetzup.models.enums.Permission;
import lombok.Data;

import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

@Data
public class PermissionDTO {

    @NotNull(message = "Permissão é obrigatória")
    @Enumerated()
    private Permission permission;

}
