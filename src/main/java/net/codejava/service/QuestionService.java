package net.codejava.service;

import net.codejava.dto.QuestionDto;
import net.codejava.entity.Question;

import java.util.List;

public interface QuestionService {
    List<Question> save(QuestionDto question, Long quizId);

}
