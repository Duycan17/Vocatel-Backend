package net.codejava.service.Impl;

import net.codejava.dto.VocabDto;
import net.codejava.entity.User;
import net.codejava.entity.Vocabulary;
import net.codejava.repository.UserRepository;
import net.codejava.repository.VocabRepository;
import net.codejava.service.VocabService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VocabServiceImpl implements VocabService {
    @Autowired
    private VocabRepository vocabRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Vocabulary save(VocabDto vocabDto) {
        Vocabulary vocabulary = modelMapper.map(vocabDto, Vocabulary.class);
        Optional<User> user = userRepository.findById(vocabDto.getUserId());
        user.ifPresent(vocabulary::setUser);
        return vocabRepository.save(vocabulary);
    }

    @Override
    public Vocabulary changeStatus(Long id) {
        Optional<Vocabulary> vocabularyOptional = vocabRepository.findById(id);
        if (vocabularyOptional.isPresent()) {
            Vocabulary vocabulary = vocabularyOptional.get();
            vocabulary.setRemember(!vocabulary.isRemember());
            return vocabRepository.saveAndFlush(vocabulary); // Update and save the changes
        }
        return null; // Handle the case when Vocabulary with the given id is not found
    }

    @Override
    public boolean deleteById(Long id) {
        try {
            vocabRepository.deleteById(id);
            return true; // Return true if deletion is successful
        } catch (Exception e) {
            // Handle any exceptions or errors here
            return false; // Return false if deletion fails
        }
    }
}
