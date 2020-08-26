package com.github.marcoscouto.instapetzup.utils;

import com.github.marcoscouto.instapetzup.models.Pet;
import com.github.marcoscouto.instapetzup.models.Post;
import com.github.marcoscouto.instapetzup.repositories.PetRepository;
import com.github.marcoscouto.instapetzup.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.Arrays;

@Configuration
@RequiredArgsConstructor
public class DatabasePopulate implements CommandLineRunner {

    private final PetRepository petRepository;
    private final PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {

        Pet pet1 = new Pet();
        pet1.setName("Rex");
        pet1.setType("Cachorro");
        pet1.setGender("Macho");
        pet1.setBreed("Salsicha");
        pet1.setBirthdate(LocalDate.of(2012, 05, 12));

        Pet pet2 = new Pet();
        pet2.setName("Juninho");
        pet2.setType("Gato");
        pet2.setGender("Macho");
        pet2.setBreed("Siames");
        pet2.setBirthdate(LocalDate.of(2007, 9, 01));

        Pet pet3 = new Pet();
        pet3.setName("Charlie");
        pet3.setType("Tartaruga");
        pet3.setGender("Macho");
        pet3.setBreed("Siberiana");
        pet3.setBirthdate(LocalDate.of(1988, 2, 04));

        petRepository.saveAll(Arrays.asList(pet1, pet2, pet3));

        pet1.getFollowing().add(pet2);
        pet1.getFollowing().add(pet3);
        pet3.getFollowing().add(pet2);
        pet2.getFollowing().add(pet1);

        // POSTS

        Post post1 = new Post();

        post1.setTitle("Primeiro post");
        post1.setText("Texto do post 1");
        post1.setAuthor(pet1);

        Post post2 = new Post();

        post2.setTitle("Segundo post");
        post2.setText("Texto do post 2");
        post2.setAuthor(pet2);

        petRepository.saveAll(Arrays.asList(pet1, pet2, pet3));
        postRepository.saveAll(Arrays.asList(
                post1,
                post2
        ));

    }
}
