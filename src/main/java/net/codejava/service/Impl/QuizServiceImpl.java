package net.codejava.service.Impl;

import net.codejava.config.ModelMapperConfig;
import net.codejava.dto.QuizDto;
import net.codejava.entity.Quiz;
import net.codejava.repository.QuizRepository;
import net.codejava.service.QuizService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuizServiceImpl implements QuizService {
    @Autowired
    private QuizRepository quizRepository;
    @Autowired
    private ModelMapperConfig modelMapper;


    @Override
    public Quiz createQuiz(QuizDto quizDto) {
        ModelMapper modelMapper = new ModelMapper();
        Quiz quiz = modelMapper.map(quizDto, Quiz.class);
        return quizRepository.save(quiz);
    }

    @Override
    public boolean deleteQuiz(Long quizId) {
        Optional<Quiz> optionalQuiz = quizRepository.findById(quizId);
        if (optionalQuiz.isPresent()) {
            Quiz quiz = optionalQuiz.get();
            quizRepository.delete(quiz);
            return true;
        }
        return false;
    }
}
