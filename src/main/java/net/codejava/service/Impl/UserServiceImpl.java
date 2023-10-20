package net.codejava.service.Impl;

import net.codejava.entity.Role;
import net.codejava.entity.User;
import net.codejava.repository.UserRepository;
import net.codejava.service.UserSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class UserServiceImpl implements UserSerivce {
    @Autowired
    private UserRepository repo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override

    public User save(User user) {
        Set<Role> roles = new HashSet<>();
        roles.add(new Role(2));
        user.setRoles(roles);
        String rawPassword = user.getPassword();
        String encodedPassword = passwordEncoder.encode(rawPassword);
        user.setPassword(encodedPassword);
        return repo.save(user);
    }


    @Override
    public User update(User user, Long id) {
        Optional<User> userToUpdate = repo.findById(id);
        if (userToUpdate.isPresent()) {
            User existingUser = userToUpdate.get();
            existingUser.setEmail(user.getEmail());
            existingUser.setPassword(user.getPassword());
            return repo.save(existingUser);
        } else {
            throw new NoSuchElementException("User not found with id: " + id);
        }
    }

    @Override
    public Boolean delete(User user) {
        try {
            repo.delete(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<User> getAllUser() {
        return repo.findAll();
    }

    @Override
    public User findUserByEmail(String email) {
        Optional<User> u = repo.findByEmail(email);
        User user = null;
        if (u.isPresent()){
            user = u.get();
        }
        return user;
    }

}
