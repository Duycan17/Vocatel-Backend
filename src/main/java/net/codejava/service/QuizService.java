package net.codejava.service;

import net.codejava.dto.QuizDto;
import net.codejava.entity.Quiz;
import org.springframework.stereotype.Service;

public interface QuizService {
    Quiz createQuiz(QuizDto quizDto);

    boolean deleteQuiz(Long quizId);
    Quiz findQuizById(Long id);
}
