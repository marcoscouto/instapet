package com.github.marcoscouto.instapetzup.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "tb_follow")
@Data
@NoArgsConstructor
public class Follow {

    @Id
    @GeneratedValue(generator = "uuid4")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "follower")
    private Pet follower;

    @ManyToOne
    @JoinColumn(name = "following")
    private Pet following;

}
