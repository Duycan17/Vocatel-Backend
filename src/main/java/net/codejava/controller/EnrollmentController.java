package net.codejava.controller;

import net.codejava.dto.EnrollmentDto;
import net.codejava.entity.Enrollment;
import net.codejava.entity.Quiz;
import net.codejava.entity.User;
import net.codejava.repository.QuizRepository;
import net.codejava.repository.UserRepository;
import net.codejava.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.*;

@RestController
@RequestMapping("/enroll")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;
    private final UserRepository userRepository;
    private final QuizRepository quizRepository;

    @Autowired
    public EnrollmentController(EnrollmentService enrollmentService, UserRepository userRepository, QuizRepository quizRepository) {
        this.enrollmentService = enrollmentService;
        this.userRepository = userRepository;
        this.quizRepository = quizRepository;
    }

    @GetMapping("/user")
    public String getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    @PostMapping("/create")
    @Transactional
    public ResponseEntity<Enrollment> createEnrollment(Authentication authentication, @RequestBody EnrollmentDto enrollmentDto) {
        authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Optional<User> u = userRepository.findByEmail(email);
        User user = u.get();
        Optional<Quiz> q = quizRepository.findById(enrollmentDto.getQuizId());
        Quiz quiz = q.get();
        enrollmentDto.setUserId(user.getId());

        Enrollment enrollment = enrollmentService.save(enrollmentDto);
        Set<Enrollment> enrollmentList = new HashSet<>();
        enrollmentList.add(enrollment);
        user.setEnrollments(enrollmentList);
        quiz.setEnrollments(enrollmentList);

       return ResponseEntity.status(HttpStatus.CREATED).body(enrollment);
        

    }

}