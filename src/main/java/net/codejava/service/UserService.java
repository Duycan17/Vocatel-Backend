package net.codejava.service;

import net.codejava.entity.User;
import net.codejava.entity.Vocabulary;

import java.security.Principal;
import java.util.List;

public interface UserService {
    User save(User user);

    User changePassword(String oldPassword, String password, Principal principal);

    User changeAvatar(String url, Principal principal);

    User changePro(Long id);

    Boolean delete(User user);

    List<User> getAllUser();

    User findUserByEmail(String email);

    List<Vocabulary> findVocabByUser(Principal principal);

    Boolean checkPro(Principal principal);
}
