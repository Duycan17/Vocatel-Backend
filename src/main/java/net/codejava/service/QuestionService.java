package net.codejava.service;

import net.codejava.dto.QuestionRequest;
import net.codejava.dto.questionResponse.QuestionResponseDTO;
import net.codejava.entity.Question;

import java.util.List;

public interface QuestionService {
    List<Question> save(QuestionRequest question, Long quizId);

    QuestionResponseDTO getAllQuestion(Long quizId);
}
