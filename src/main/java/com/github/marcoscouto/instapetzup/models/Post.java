package com.github.marcoscouto.instapetzup.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_posts")
@Data
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(generator = "uuid4")
    private UUID id;

    private String title;

    //@Lob
    private String text;

    private Integer likes;

    @CreatedDate
    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet author;

}
