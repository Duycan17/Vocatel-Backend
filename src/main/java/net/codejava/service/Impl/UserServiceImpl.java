package net.codejava.service.Impl;
import org.apache.commons.codec.digest.DigestUtils;
import net.codejava.entity.Role;
import net.codejava.entity.User;
import net.codejava.entity.Vocabulary;
import net.codejava.repository.UserRepository;
import net.codejava.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import javax.transaction.Transactional;
import java.nio.charset.StandardCharsets;
import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository repo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override

    public User save(User user) {
        Set<Role> roles = new HashSet<>();
        roles.add(new Role(1));
        user.setRoles(roles);
        String rawPassword = user.getPassword();
        String encodedPassword = passwordEncoder.encode(rawPassword);
        user.setPassword(encodedPassword);
        return repo.save(user);
    }


    @Override
    public User changePassword(String oldPassword, String password, Principal principal) {
        String email = principal.getName();
        Optional<User> user = repo.findByEmail(email);
        String encodedPassword = passwordEncoder.encode(password);

        if (user.isPresent()) {
            User existingUser = user.get();
            if (!passwordEncoder.matches(oldPassword, existingUser.getPassword())) {
                throw new NoSuchElementException("Wrong password");
            }
            existingUser.setPassword(encodedPassword);
            return repo.save(existingUser);
        } else {
            throw new NoSuchElementException("User not found with email: " + email);
        }
    }

    @Override
    public User forgotPassword(String email) {
    String otp = generateOTP();

        Optional<User> user = repo.findByEmail(email);
        String encodedPassword = passwordEncoder.encode(otp);

        if (user.isPresent()) {
            User existingUser = user.get();
            existingUser.setPassword(encodedPassword);
            sendEmail(email,"Your new password is: ",otp);
            System.out.println("Your new password is "+otp);
            return repo.save(existingUser);
        } else {
            throw new NoSuchElementException("User not found with email: " + email);
        }
    }

    @Override
    public User changeAvatar(String url, Principal principal) {
        String email = principal.getName();
        Optional<User> user = repo.findByEmail(email);
        if (user.isPresent()) {
            User existingUser = user.get();
            byte[] avatarBytes = url.getBytes(StandardCharsets.UTF_8); // Convert String to byte[]
            existingUser.setAvatarUrl(avatarBytes);
            return repo.save(existingUser);
        } else {
            throw new NoSuchElementException("User not found with email: " + email);
        }
    }

    @Override
    public User changePro(Long id) {
        Optional<User> user = repo.findById(id);
        if (user.isPresent()) {
            User existingUser = user.get();
            existingUser.setPro(!existingUser.getPro());
            return repo.save(existingUser);
        } else {
            throw new NoSuchElementException("User not found with email: " + id);
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
        if (u.isPresent()) {
            user = u.get();
        }
        return user;
    }

    @Override
    public List<Vocabulary> findVocabByUser(Principal principal) {
        String email = principal.getName();
        User user = this.findUserByEmail(email);
        return user.getVocabularies().stream().collect(Collectors.toList());
    }

    @Override
    public Boolean checkPro(Principal principal) {
        String email = principal.getName();
        User user = this.findUserByEmail(email);
        return user.getPro();
    }

    @Override
    public List<Vocabulary> findVocabNotRememberByUser(Principal principal) {
        String email = principal.getName();
        User user = this.findUserByEmail(email);
        List<Vocabulary> notRememberedVocabularies = user.getVocabularies().stream()
                .filter(vocabulary -> !vocabulary.isRemember())
                .collect(Collectors.toList());
        return notRememberedVocabularies;
    }
    @Override
    public Boolean sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("tranghuyen9696@gmail.com");

        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        javaMailSender.send(message);
        System.out.println("Send mail successfully");
        return true;
    }
    public  String generateOTP() {
        // Tạo một chuỗi ngẫu nhiên
        String randomString = "your-random-string"; // Thay thế bằng chuỗi ngẫu nhiên thực tế

        // Áp dụng thuật toán SHA-256 để mã hóa chuỗi ngẫu nhiên
        String otp = DigestUtils.sha256Hex(randomString);

        // Trích xuất 6 ký tự đầu tiên của mã OTP
        otp = otp.substring(0, 6);

        return otp;
    }
}
