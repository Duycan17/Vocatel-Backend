package net.codejava.dto.questionResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionResponseDTO {
    private int responseCode;
    private List<subQuestionDto> results;
}