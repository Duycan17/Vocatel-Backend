package net.codejava.controller;

import net.codejava.dto.VocabDto;
import net.codejava.entity.User;
import net.codejava.entity.Vocabulary;
import net.codejava.service.UserService;
import net.codejava.service.VocabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/vocab")
@RolesAllowed("USER")
public class VocabController {
    @Autowired
    private VocabService vocabService;
    @Autowired
    private UserService userService;

    @GetMapping()
    public ResponseEntity<List<Vocabulary>> getAllVocab(Principal principal) {
        List<Vocabulary> vocabularies = userService.findVocabByUser(principal);
        if (vocabularies.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(vocabularies, HttpStatus.OK);
        }
    }

    @PostMapping()
    public ResponseEntity<Vocabulary> save(Authentication authentication, @RequestBody VocabDto vocabDto) {
        authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userService.findUserByEmail(email);
        vocabDto.setUserId(user.getId());
        Vocabulary savedVocabulary = vocabService.save(vocabDto);
        return ResponseEntity.ok(savedVocabulary);
    }

    @PostMapping("/remember")
    public ResponseEntity<Vocabulary> changeStatus(@RequestParam Long id) {
        Vocabulary vocabulary = vocabService.changeStatus(id);
        if (vocabulary != null) {
            return ResponseEntity.ok(vocabulary);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping()
    public ResponseEntity<Boolean> deleteVocab(@RequestParam Long id) {
        boolean isDeleted = vocabService.deleteById(id);
        return ResponseEntity.ok(isDeleted);
    }

}
