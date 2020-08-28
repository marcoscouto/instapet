package com.github.marcoscouto.instapetzup.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "tb_comments")
@Data
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(generator = "uuid4")
    private UUID id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet author;

    private String text;

}
