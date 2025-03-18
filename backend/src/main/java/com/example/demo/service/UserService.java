package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.util.EncryptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EncryptionUtil encryptionUtil;
    public User createUser(User user) {
        Integer lastId = userRepository.findTopByOrderByIdDesc().getId();
        if (lastId != null) {
            user.setId(lastId + 1);
        } else {
            user.setId(1); // 如果表中没有数据，则设置为 1
        }
        return userRepository.save(user);
    }

    public User getUserByUserId(Integer userid) {
        Optional<User> userOptional = userRepository.findById(userid);
        if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
            throw new RuntimeException("Username not found");
        }
    }
    public User getUserByUserName(String username) {
        return  userRepository.findByUsername(username);
    }

    public Iterable<User> getAllUsers() {
        return userRepository.findAllUsers();
    }


    public void deleteUser(Integer userid) {
        userRepository.deleteById(userid);
    }

    // 新增更新方法
    public User updateUserName(Integer userid, String newUsername) {
        User user = userRepository.findById(userid).orElseThrow(() -> new RuntimeException("User not found"));
        user.setUserName(newUsername);
        return userRepository.save(user);
    }

    public User updatePassword(Integer userid, String newPassword) {
        User user = userRepository.findById(userid).orElseThrow(() -> new RuntimeException("User not found"));
        user.setPassword(newPassword);
        return userRepository.save(user);
    }

    public User updateRole(Integer userid, String newRole) {
        User user = userRepository.findById(userid).orElseThrow(() -> new RuntimeException("User not found"));
        user.setRole(newRole);
        return userRepository.save(user);
    }

    public User updateEmail(Integer userid, String newEmail) {
        User user = userRepository.findById(userid).orElseThrow(() -> new RuntimeException("User not found"));
        user.setEmail(newEmail);
        return userRepository.save(user);
    }

    public User updatePhone(Integer userid, String newPhone) {
        User user = userRepository.findById(userid).orElseThrow(() -> new RuntimeException("User not found"));
        user.setPhone(newPhone);
        return userRepository.save(user);
    }

    public User updateAll(Integer userid, String username, String password, String role, String email, String phone) {
        User user = userRepository.findById(userid).orElseThrow(() -> new RuntimeException("User not found"));
        if (username != null) user.setUserName(username);
        if (password != null) user.setPassword(password);
        if (role != null) user.setRole(role);
        if (email != null) user.setEmail(email);
        if (phone != null) user.setPhone(phone);
        return userRepository.save(user);
    }
}