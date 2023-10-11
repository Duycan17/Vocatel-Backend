package net.codejava.service;

import javax.transaction.Transactional;

import net.codejava.entity.Role;
import net.codejava.entity.User;
import net.codejava.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class UserService {
	@Autowired private UserRepository repo;
	@Autowired private PasswordEncoder passwordEncoder;

	public User save(User user) {
		Set<Role> roles = new HashSet<>();
		roles.add(new Role(2));
		user.setRoles(roles);
		String rawPassword = user.getPassword();
		String encodedPassword = passwordEncoder.encode(rawPassword);
		user.setPassword(encodedPassword);
		return repo.save(user);
	}
}
