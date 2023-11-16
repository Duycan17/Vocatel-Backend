package net.codejava.service;

import net.codejava.dto.VocabDto;
import net.codejava.entity.Vocabulary;

public interface VocabService {
    Vocabulary save(VocabDto vocabDto);
    Vocabulary changeStatus(Long id);

}
