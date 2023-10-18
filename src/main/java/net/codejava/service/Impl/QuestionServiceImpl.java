package net.codejava.service.Impl;

import net.codejava.dto.QuestionDto;
import net.codejava.entity.Question;
import net.codejava.entity.Quiz;
import net.codejava.repository.QuestionRepository;
import net.codejava.repository.QuizRepository;
import net.codejava.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuizRepository quizRepository;

    @Override
    public List<Question> save(QuestionDto questionDto) {
        List<String> questions = questionDto.getQuestions();
        List<String> correctAnswers = questionDto.getCorrectAnswers();
        List<String> option1 = questionDto.getOption1();
        List<String> option2 = questionDto.getOption2();
        List<String> option3 = questionDto.getOption3();
        List<String> option4 = questionDto.getOption4();
        Long quizId = questionDto.getQuizId();
        Optional<Quiz> quiz = quizRepository.findById(quizId);
        Quiz quizCreated = null;
        if (quiz.isPresent()) {
            quizCreated = quiz.get();
        }
        List<Question> newQuestions = new ArrayList<>();
        for (int i = 0; i < questions.size(); i++) {
            Question question = new Question();
            question.setQuestion(questions.get(i));
            question.setCorrectAnswer(correctAnswers.get(i));
            question.setOption1(option1.get(i));
            question.setOption2(option2.get(i));
            question.setOption3(option3.get(i));
            question.setOption4(option4.get(i));
            question.setQuizId(quizCreated);
            newQuestions.add(question);
        }
        return questionRepository.saveAll(newQuestions);
    }
}