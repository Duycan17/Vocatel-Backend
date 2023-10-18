package net.codejava.controller;

import net.codejava.dto.AuthRequest;
import net.codejava.dto.AuthResponse;
import net.codejava.dto.UserDto;
import net.codejava.entity.User;
import net.codejava.jwt.JwtTokenUtil;
import net.codejava.service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.security.Principal;


@RestController
@CrossOrigin
@RequestMapping("/auth")
public class UserController {

    @Autowired
    AuthenticationManager authManager;
    @Autowired
    JwtTokenUtil jwtUtil;
    @Autowired
    private UserServiceImpl service;

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody @Valid User user) {
        User createdUser = service.save(user);
        URI uri = URI.create("/users/" + createdUser.getId());
        UserDto userDto = new UserDto(createdUser.getId(), createdUser.getEmail());
        return ResponseEntity.created(uri).body(userDto);
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthRequest request) {
        try {
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(), request.getPassword())
            );

            User user = (User) authentication.getPrincipal();
            String accessToken = jwtUtil.generateAccessToken(user);
            AuthResponse response = new AuthResponse(user.getEmail(), accessToken);

            return ResponseEntity.ok().body(response);

        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
