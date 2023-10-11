package net.codejava.repository;

import java.util.Optional;

import net.codejava.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	Optional<User> findByEmail(String email);
	
}
