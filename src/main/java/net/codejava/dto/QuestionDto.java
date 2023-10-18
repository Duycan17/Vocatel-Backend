package net.codejava.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDto {
    private List<String> questions;
    private List<String> correctAnswers;
    private List<String> option1;
    private List<String> option2;
    private List<String> option3;
    private List<String> option4;
    private Long quizId;
}