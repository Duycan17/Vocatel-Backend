package net.codejava.user.api;

import javax.transaction.Transactional;

import net.codejava.user.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import net.codejava.user.User;
import net.codejava.user.UserRepository;

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
