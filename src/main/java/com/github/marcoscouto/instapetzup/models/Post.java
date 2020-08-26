package com.github.marcoscouto.instapetzup.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_posts")
@Data
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(generator = "uuid4")
    private UUID id;

    @NotBlank(message = "Título é obrigatório")
    private String title;

    @NotBlank(message = "Texto é obrigatório")
    private String text;

    private Integer likes;

    @ElementCollection
    @CollectionTable(name = "tb_comments", joinColumns = @JoinColumn(name = "post_id"))
    @Column(name = "comment")
    private List<String> comments;

    @CreatedDate
    private LocalDateTime timestamp;
}
