package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.example.demo.util.EncryptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private EncryptionUtil encryptionUtil;

    @PostMapping
    public User createUser(@RequestBody User user) {
        String encryptedUsername = encryptionUtil.encrypt(user.getUserName());
        user.setUserName(encryptedUsername);
        String encryptedPassword = encryptionUtil.encrypt(user.getPassword());
        user.setPassword(encryptedPassword);
        return userService.createUser(user);
    }

    @GetMapping("/{id}")
    public User getUserByUserId(@PathVariable Integer id) {
        return userService.getUserByUserId(id);
    }

    @GetMapping("/{username}")
    public User getUserByUserN(@PathVariable String username) {
        return userService.getUserByUserName(username);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return (List<User>) userService.getAllUsers();
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
    }

    // 新增更新方法
    @PutMapping("/updateUsername/{id}")
    public User updateUserName(@PathVariable Integer id, @RequestBody String newUsername) {
        return userService.updateUserName(id, newUsername);
    }

    @PutMapping("/updatePassword/{id}")
    public User updatePassword(@PathVariable Integer id, @RequestBody String newPassword) {
        return userService.updatePassword(id, newPassword);
    }

    @PutMapping("/updateRole/{id}")
    public User updateRole(@PathVariable Integer userid, @RequestBody String newRole) {
        return userService.updateRole(userid, newRole);
    }

    @PutMapping("/updateEmail/{id}")
    public User updateEmail(@PathVariable Integer id, @RequestBody String newEmail) {
        return userService.updateEmail(id, newEmail);
    }

    @PutMapping("/updatePhone/{id}")
    public User updatePhone(@PathVariable Integer id, @RequestBody String newPhone) {
        return userService.updatePhone(id, newPhone);
    }

    @PutMapping("/updateAll/{id}")
    public User updateAll(@PathVariable Integer id, @RequestBody User user) {
        return userService.updateAll(id, user.getUserName(), user.getPassword(), user.getRole(), user.getEmail(), user.getPhone());
    }
}
