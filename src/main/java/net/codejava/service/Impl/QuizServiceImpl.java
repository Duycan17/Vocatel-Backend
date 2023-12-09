package net.codejava.service.Impl;

import net.codejava.config.ModelMapperConfig;
import net.codejava.dto.QuizDto;
import net.codejava.entity.Quiz;
import net.codejava.repository.QuizRepository;
import net.codejava.service.QuizService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    @Override
    public Quiz findQuizById(Long id) {
        Optional<Quiz> q = quizRepository.findById(id);
        Quiz quiz = null;
        if (q.isPresent()) {
            quiz = q.get();
        }
        return quiz;
    }

    @Override
    public List<Quiz> getAllQuiz() {
        return quizRepository.findAll();
    }

    @Override
    public Quiz getQuiz(Long id) {
        Optional quiz = quizRepository.findById(id);
        return (Quiz) quiz.get();
    }
}
