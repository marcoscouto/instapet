package com.github.marcoscouto.instapetzup.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class StandardError {

    private String title;
    private List<String> messages;
    private LocalDateTime timestamp;

}
