package com.github.marcoscouto.instapetzup.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class PostDTO {

    @NotBlank(message = "Título é obrigatório")
    private String title;

    @NotBlank(message = "Texto é obrigatório")
    private String text;

    @NotNull(message = "Id do autor é obrigatório")
    private UUID author;
}
