package net.codejava.controller;

import net.codejava.dto.QuestionDto;
import net.codejava.dto.QuizDto;
import net.codejava.entity.Quiz;
import net.codejava.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quiz")
public class QuizController {
    @Autowired
    private QuizService quizService;

    @PostMapping("/create")
    public ResponseEntity<Quiz> createQuiz(@RequestBody QuizDto quizDto) {
        Quiz createdQuiz = quizService.createQuiz(quizDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdQuiz);
    }

    @PostMapping("/create")
    public ResponseEntity<Quiz> createQuiz(@RequestParam("id") Long quizId, @RequestBody QuestionDto questionDto) {
        Quiz createdQuiz = quizService.createQuiz(quizDto);

        // Associate the created question with the specified quiz ID
        QuestionDto questionDto = quizDto.getQuestion();
        questionDto.setQuizId(quizId);
        questionService.createQuestion(questionDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdQuiz);
    }
}