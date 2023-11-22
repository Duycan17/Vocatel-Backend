package net.codejava.service.Impl;

import net.codejava.dto.QuestionRequest;
import net.codejava.dto.questionResponse.QuestionResponseDTO;
import net.codejava.dto.questionResponse.subQuestionDto;
import net.codejava.entity.Question;
import net.codejava.entity.Quiz;
import net.codejava.repository.QuestionRepository;
import net.codejava.repository.QuizRepository;
import net.codejava.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuizRepository quizRepository;

    @Override
    public List<Question> save(QuestionRequest questionRequest, Long quizId) {
        List<String> questions = questionRequest.getQuestions();
        List<String> correctAnswers = questionRequest.getCorrectAnswers();
        List<String> option1 = questionRequest.getOption1();
        List<String> option2 = questionRequest.getOption2();
        List<String> option3 = questionRequest.getOption3();
        List<String> option4 = questionRequest.getOption4();
        Optional<Quiz> quiz = quizRepository.findById(quizId);
        Quiz quizCreated = null;
        if (quiz.isPresent()) {
            quizCreated = quiz.get();
        } else {
            return null;
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

    @Override
    public QuestionResponseDTO getAllQuestion(Long quizId) {
        List<Question> temp;
        temp = questionRepository.findAllByQuizId(quizRepository.findById(quizId));
        QuestionResponseDTO questions = new QuestionResponseDTO();
        questions.setResponseCode(0);
        List<subQuestionDto> subQuestionDtos = null;
        subQuestionDtos = new ArrayList<>();
        for (Question index : temp) {
            subQuestionDto subQuestionDto = new subQuestionDto();
            subQuestionDto.setCategory("English");
            subQuestionDto.setType("multiple");
            subQuestionDto.setDifficulty("Entertainment: Music");
            subQuestionDto.setQuestion(index.getQuestion());
            subQuestionDto.setCorrect_answer(index.getCorrectAnswer());
            List<String> incorrectAnswer = new ArrayList<>();
            if (!index.getCorrectAnswer().equals(index.getOption4())) {
                incorrectAnswer.add(index.getOption4());
            }
            if (!index.getCorrectAnswer().equals(index.getOption1())) {
                incorrectAnswer.add(index.getOption1());
            }
            if (!index.getCorrectAnswer().equals(index.getOption2())) {
                incorrectAnswer.add(index.getOption2());
            }
            if (!index.getCorrectAnswer().equals(index.getOption3())) {
                incorrectAnswer.add(index.getOption3());
            }
            subQuestionDto.setIncorrect_answers(
                    incorrectAnswer
            );
            subQuestionDtos.add(subQuestionDto);
        }
        questions.setResults(subQuestionDtos);
        return questions;
    }
}