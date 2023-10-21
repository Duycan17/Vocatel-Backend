package net.codejava.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuizDto {
    private String title;
    private String difficultyLevel;
    private int maxTime;
}