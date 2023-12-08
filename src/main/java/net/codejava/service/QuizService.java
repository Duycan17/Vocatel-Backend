package net.codejava.service;

import net.codejava.dto.QuizDto;
import net.codejava.entity.Quiz;
import net.codejava.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface QuizService {
    Quiz createQuiz(QuizDto quizDto);

    boolean deleteQuiz(Long quizId);
    Quiz findQuizById(Long id);

    List<Quiz> getAllQuiz();
}
