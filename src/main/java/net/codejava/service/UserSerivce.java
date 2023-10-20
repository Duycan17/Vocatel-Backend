package net.codejava.service;

import net.codejava.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserSerivce {
    User save(User user);

    User update(User user, Long id);

    Boolean delete(User user);

    List<User> getAllUser();

    User findUserByEmail(String email);

}
