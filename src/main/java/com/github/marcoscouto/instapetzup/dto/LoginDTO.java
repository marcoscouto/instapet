package com.github.marcoscouto.instapetzup.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class LoginDTO {

    @NotBlank(message = "Email é obrigatório")
    @Email(message = "O email tem que ser válido")
    private String email;

    @NotBlank(message = "Senha é obrigatória")
    private String password;

}
