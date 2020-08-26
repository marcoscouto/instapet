package com.github.marcoscouto.instapetzup.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class PetDTO {

    @NotBlank(message = "Nome é obrigatório")
    @Size(max = 100, message = "Tamanho máximo do nome é de 100 caracteres")
    private String name;

    @NotBlank(message = "Tipo é obrigatório")
    @Size(max = 50, message = "Tamanho máximo do tipo é de 50 caracteres")
    private String type;

    @NotBlank(message = "Raça é obrigatória")
    @Size(max = 50, message = "Tamanho máximo da raça é de 50 caracteres")
    private String breed;

    @Size(max = 50, message = "Tamanho máximo do gênero é de 50 caracteres")
    private String gender;

    @PastOrPresent(message = "A data de nascimento tem que ser presente ou passado")
    private LocalDate birthdate;

}
