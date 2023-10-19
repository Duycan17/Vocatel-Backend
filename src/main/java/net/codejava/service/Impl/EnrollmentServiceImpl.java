package net.codejava.service.Impl;

import net.codejava.dto.EnrollmentDto;
import net.codejava.entity.Enrollment;
import net.codejava.entity.Quiz;
import net.codejava.entity.User;
import net.codejava.repository.EnrollmentRepository;
import net.codejava.repository.QuizRepository;
import net.codejava.repository.UserRepository;
import net.codejava.service.EnrollmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {
    @Autowired
    private EnrollmentRepository enrollmentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private QuizRepository quizRepository;
    @Override
    public Enrollment save(EnrollmentDto enrollmentDto) {
        Enrollment enrollment = new Enrollment();
        Optional<User> userOptional = userRepository.findById(enrollmentDto.getUserId());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            enrollment.setUser(user);
        }
        Optional<Quiz> quizOptional = quizRepository.findById(enrollmentDto.getQuizId());
        if (quizOptional.isPresent()) {
            Quiz quiz = quizOptional.get();
            enrollment.setQuiz(quiz);
        }
        enrollment.setScore(enrollmentDto.getScore());
        return enrollmentRepository.save(enrollment);
    }
}
