package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.example.demo.util.AuthRequest;
import com.example.demo.util.EncryptionUtil;
import com.example.demo.util.JwtTokenUtil;
import org.jasypt.util.text.AES256TextEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {
  @Autowired
  private UserService userService;
  @Autowired
  private EncryptionUtil encryptionUtil;

  @Autowired
  JwtTokenUtil jwtTokenUtil;

  @PostMapping
  public User createUser(@RequestBody User user) {
    String encryptedUsername = encryptionUtil.encrypt(user.getUserName());
    user.setUserName(encryptedUsername);
    String encryptedPassword = encryptionUtil.encrypt(user.getPassword());
    user.setPassword(encryptedPassword);
    return userService.createUser(user);
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) throws Exception {
    User user = userService.getUserByUserName(authRequest.getUsername());
    String token = jwtTokenUtil.generateToken(authRequest.getUsername());
    if(user!=null && user.getPassword().equals(authRequest.getPassword())){
      Map <String,String> response = new HashMap<>();
      response.put("token",token);
      return ResponseEntity.ok(response);
    }else{
      throw new RuntimeException("Invalid cre!");
    }
  }
  private String decrypt(String encryptedValue) {
    // 创建 Jasypt 加密器
    AES256TextEncryptor textEncryptor = new AES256TextEncryptor();
    textEncryptor.setPassword("yourSecretKey"); // 设置您的加密密钥
    return textEncryptor.decrypt(encryptedValue);
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
