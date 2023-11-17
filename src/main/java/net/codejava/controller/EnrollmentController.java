package net.codejava.controller;

import net.codejava.dto.EnrollmentDto;
import net.codejava.entity.Enrollment;
import net.codejava.entity.Quiz;
import net.codejava.entity.User;
import net.codejava.service.EnrollmentService;
import net.codejava.service.QuizService;
import net.codejava.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/quiz/enrollment")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;
    private final UserService userService;
    private final QuizService quizService;

    @Autowired
    public EnrollmentController(EnrollmentService enrollmentService, UserService userService, QuizService quizService) {
        this.enrollmentService = enrollmentService;
        this.userService = userService;
        this.quizService = quizService;
    }

    @PostMapping()
    @Transactional
    public ResponseEntity<Enrollment> createEnrollment(Authentication authentication, @RequestBody EnrollmentDto enrollmentDto) {
        authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userService.findUserByEmail(email);
        Quiz quiz = quizService.findQuizById(enrollmentDto.getQuizId());
        enrollmentDto.setUserId(user.getId());
        Enrollment enrollment = enrollmentService.save(enrollmentDto);
        Set<Enrollment> enrollmentList = new HashSet<>();
        enrollmentList.add(enrollment);
        user.setEnrollments(enrollmentList);
        quiz.setEnrollments(enrollmentList);
        if (enrollment != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(enrollment);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

}