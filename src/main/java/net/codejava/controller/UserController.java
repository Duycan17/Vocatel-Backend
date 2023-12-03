package net.codejava.controller;

import net.codejava.dto.modifyUser.ChangeAvatarDTO;
import net.codejava.dto.modifyUser.ChangePasswordDTO;
import net.codejava.dto.modifyUser.ChangeProDTO;
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
        // Implementation
        return ResponseEntity.ok(userService.changeAvatar(changeAvatarDTO.getUrl(), principal));
    }
    @RolesAllowed("ADMIN")
    @PatchMapping("/pro")
    public ResponseEntity<User> changePro(@RequestParam("id") Long userId) {
        return ResponseEntity.ok(userService.changePro(userId));
    }
//
//    @GetMapping
//    public ResponseEntity<List<User>> getAllUser() {
//        List<User> users = userService.getAllUser();
//        return ResponseEntity.ok(users);
//    }


}

