package net.codejava.controller;

import net.codejava.dto.QuizDto;
import net.codejava.entity.Quiz;
import net.codejava.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/quiz")
@RolesAllowed("ADMIN")
public class QuizController {
    @Autowired
    private QuizService quizService;

    @PostMapping()
    public ResponseEntity<Quiz> createQuiz(@RequestBody QuizDto quizDto) {
        Quiz createdQuiz = quizService.createQuiz(quizDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdQuiz);
    }

}