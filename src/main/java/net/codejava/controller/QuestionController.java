package net.codejava.controller;

import net.codejava.dto.QuestionRequest;
import net.codejava.dto.questionResponse.QuestionResponseDTO;
import net.codejava.entity.Question;
import net.codejava.service.QuestionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping()
    public ResponseEntity<List<Question>> saveQuestion(@RequestParam("id") Long quizId, @RequestBody QuestionRequest questionRequest) {
        List<Question> savedQuestions = questionService.save(questionRequest, quizId);
        if (savedQuestions != null && !savedQuestions.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(savedQuestions);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<QuestionResponseDTO> getQuestion(@RequestParam("id") Long quizId) {
        return ResponseEntity.status(HttpStatus.OK).body(questionService.getAllQuestion(quizId));
    }
}
