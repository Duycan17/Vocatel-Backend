package net.codejava.repository;

import net.codejava.entity.Vocabulary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VocabRepository extends JpaRepository<Vocabulary, Long> {
}
