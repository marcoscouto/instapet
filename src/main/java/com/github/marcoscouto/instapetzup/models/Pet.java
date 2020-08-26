package com.github.marcoscouto.instapetzup.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "tb_pets")
@Data
@NoArgsConstructor
public class Pet {

    @Id
    @GeneratedValue(generator = "uuid4")
    private UUID id;

    private String name;
    private String type;
    private String breed;
    private String gender;
    private LocalDate birthdate;

    @JsonIgnore
    @OneToMany(mappedBy = "author")
    private Set<Post> posts = new HashSet<>();

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "tb_pet_follows", joinColumns = @JoinColumn(name = "followers"))
    private List<Pet> following = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "following")
    private List<Pet> followers = new ArrayList<>();


}
