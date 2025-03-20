package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.util.EncryptionUtil;
import org.jasypt.util.text.AES256TextEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EncryptionUtil encryptionUtil;
    @Value("${jasypt.encryptor.password}")
    private String encryptorPassword;
    public User createUser(User user) {
        Integer lastId = userRepository.findTopByOrderByIdDesc().getId();
        System.out.println( "lastId:" + lastId );
        if (lastId != null) {
            user.setId(lastId + 1);
        } else {
            user.setId(1); // 如果表中没有数据，则设置为 1
        }
        return userRepository.save(user);
    }

    private String encrypt(String plainText) {
        AES256TextEncryptor textEncryptor = new AES256TextEncryptor();
        textEncryptor.setPassword(encryptorPassword);
        return textEncryptor.encrypt(plainText);
    }

    private String decrypt(String encryptedValue) {
        try {
            AES256TextEncryptor textEncryptor = new AES256TextEncryptor();
            textEncryptor.setPassword(encryptorPassword);
            return textEncryptor.decrypt(encryptedValue);
        } catch (Exception e) {
            System.err.println("Decryption failed: " + e.getMessage());
            throw e; // 或者处理异常
        }
    }

    public void print(String userName, String inputPassword){
        User user = userRepository.findByUsername(userName);
        String decryptedPassword = decrypt(user.getPassword());
        System.out.println("inputPassword:"+inputPassword);
        System.out.println("decryptedPassword:"+decryptedPassword);
        System.out.println("result:"+ decryptedPassword.equals(inputPassword));
    }
    public boolean checkCredentials(String userName, String inputPassword) {
        // 从数据库中获取用户
        User user = userRepository.findByUsername(userName);
        if (user == null) {
            return false; // 用户不存在
        }
        String decryptedPassword = decrypt(user.getPassword());
        System.out.println("inputPassword:"+inputPassword);
        System.out.println("decryptedPassword:"+decryptedPassword);
        System.out.println("result:"+ decryptedPassword.equals(inputPassword));
        // 验证用户名和密码
        return  decryptedPassword.equals(inputPassword);
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