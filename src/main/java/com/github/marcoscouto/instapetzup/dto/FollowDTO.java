package com.github.marcoscouto.instapetzup.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class FollowDTO {

    @NotNull(message = "Seguidor não pode ser nulo")
    private UUID follower;

    @NotNull(message = "Seguido não pode ser nulo")
    private UUID following;

}
