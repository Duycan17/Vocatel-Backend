package net.codejava.controller;

import net.codejava.dto.modifyUser.ChangeAvatarDTO;
import net.codejava.dto.modifyUser.ChangePasswordDTO;
import net.codejava.entity.User;
import net.codejava.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/users")

@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    @RolesAllowed("USER")
    @PatchMapping("/password")
    public ResponseEntity<User> changePassword(@RequestBody ChangePasswordDTO changePasswordDTO, Principal principal) {
        User user = userService.changePassword(changePasswordDTO.getOldPassword(), changePasswordDTO.getPassword(), principal);
        return ResponseEntity.ok(user);
    }

    @RolesAllowed("USER")
    @PatchMapping("/avatar")
    public ResponseEntity<User> changeAvatar(@RequestBody ChangeAvatarDTO changeAvatarDTO, Principal principal) {
        return ResponseEntity.ok(userService.changeAvatar(changeAvatarDTO.getUrl(), principal));
    }

    @RolesAllowed("ADMIN")
    @PatchMapping("/pro")
    public ResponseEntity<User> changePro(@RequestParam("id") Long userId) {
        return ResponseEntity.ok(userService.changePro(userId));
    }

    @PatchMapping("/pro-email")
    public ResponseEntity<User> changeProByEmail(@RequestParam("email") String email) {
        return ResponseEntity.ok(userService.changePro(email));
    }

    @RolesAllowed(("USER"))
    @GetMapping("/isPro")
    public ResponseEntity<Boolean> isPro(Principal principal) {
        return ResponseEntity.ok(userService.checkPro(principal));
    }

    @RolesAllowed("ADMIN")
    @GetMapping()
    public ResponseEntity<Object> getAllUser() {
        List<User> users = userService.getAllUser();
        return ResponseEntity.ok(users);
    }



}

