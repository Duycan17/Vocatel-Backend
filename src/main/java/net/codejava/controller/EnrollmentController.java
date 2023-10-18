package net.codejava.controller;

import net.codejava.dto.EnrollmentDto;
import net.codejava.entity.Enrollment;
import net.codejava.entity.User;
import net.codejava.repository.UserRepository;
import net.codejava.service.EnrollmentService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("/enroll")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;
    @Autowired
    private UserRepository userRepository;

    private Principal principal;
    @GetMapping
    public String getCurrentUser(HttpServletRequest request){
        principal = request.getUserPrincipal();
        return principal.getName();
    }
    @PostMapping("/create")
    public ResponseEntity<Enrollment> createEnrollment(HttpServletRequest request, @RequestBody EnrollmentDto enrollmentDto){
        principal = request.getUserPrincipal();
        Optional<User> userOptional = userRepository.findByEmail(principal.getName());
        User user = null;
        if (userOptional.isPresent()){
             user = userOptional.get();
        }
        enrollmentDto.setUserId(user.getId());
        Enrollment enrollment = enrollmentService.save(enrollmentDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(enrollment);
    }
}
