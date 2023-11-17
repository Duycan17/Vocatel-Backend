package net.codejava.controller;

import net.codejava.dto.VocabDto;
import net.codejava.entity.User;
import net.codejava.entity.Vocabulary;
import net.codejava.service.UserService;
import net.codejava.service.VocabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vocab")
public class VocabController {
    @Autowired
    private VocabService vocabService;
    @Autowired
    private UserService userService;

    @PostMapping()
    public ResponseEntity<Vocabulary> save(Authentication authentication, @RequestBody VocabDto vocabDto) {
        authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userService.findUserByEmail(email);
        vocabDto.setUserId(user.getId());
        Vocabulary savedVocabulary = vocabService.save(vocabDto);
        return ResponseEntity.ok(savedVocabulary);
    }

    @PostMapping("remember")
    public ResponseEntity<Vocabulary> changeStatus(@RequestParam Long id) {
        Vocabulary vocabulary = vocabService.changeStatus(id);
        if (vocabulary != null) {
            return ResponseEntity.ok(vocabulary);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
