package net.codejava.service;

import net.codejava.dto.QuizDto;
import net.codejava.entity.Quiz;

public interface QuizService {
    Quiz createQuiz(QuizDto quizDto);

    boolean deleteQuiz(Long quizId);
}
