package com.github.marcoscouto.instapetzup.models;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "tb_pets")
@Data
@NoArgsConstructor
@Builder
public class Pet {

    @Id
    @GeneratedValue(generator = "uuid4")
    private UUID id;

    @NotBlank(message = "Nome é obrigatório")
    @Max(value = 100, message = "Tamanho máximo do nome é de 100 caracteres")
    private String name;

    @NotBlank(message = "Tipo é obrigatório")
    @Max(value = 50, message = "Tamanho máximo do tipo é de 50 caracteres")
    private String type;

    @NotBlank(message = "Raça é obrigatória")
    @Max(value = 50, message = "Tamanho máximo da raça é de 50 caracteres")
    private String breed;

    @Max(value = 50, message = "Tamanho máximo do gênero é de 50 caracteres")
    private String gender;

    @PastOrPresent(message = "A data de nascimento tem que ser presente ou passado")
    private Integer birthdate;

    @ElementCollection
    @CollectionTable(name="tb_friends", joinColumns = @JoinColumn(name = "friend_id"))
    @Column(name = "friends")
    private Set<UUID> friends = new HashSet<>();

}
