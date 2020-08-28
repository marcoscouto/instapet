package com.github.marcoscouto.instapetzup.services.impl;

import com.github.marcoscouto.instapetzup.exceptions.NotFoundException;
import com.github.marcoscouto.instapetzup.models.Pet;
import com.github.marcoscouto.instapetzup.repositories.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final PetRepository petRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Pet pet = petRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Pet não encontrado - Email: " + email));
        return User.builder()
                .username(pet.getEmail())
                .password(pet.getPassword())
                .roles(pet.getPermission().toString())
                .build();
    }
}
