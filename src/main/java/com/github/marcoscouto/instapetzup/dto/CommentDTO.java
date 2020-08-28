package com.github.marcoscouto.instapetzup.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
public class CommentDTO {

    @NotNull(message = "Post é obrigatório")
    private UUID post;

    @NotNull(message = "Autor é obrigatório")
    private UUID author;

    @NotBlank(message = "Texto é obrigatório")
    @Size(max = 150, message = "O texto tem que tem no máximo 150 caracteres")
    private String text;

}
