package com.github.marcoscouto.instapetzup.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "tb_pets")
@Data
@NoArgsConstructor
public class Pet {

    @Id
    @GeneratedValue(generator = "uuid4")
    private UUID id;

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

    @ElementCollection
    @CollectionTable(name="tb_friends", joinColumns = @JoinColumn(name = "pet_id"))
    @Column(name = "friend")
    private Set<UUID> friends = new HashSet<>();

}
