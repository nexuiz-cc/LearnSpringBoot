package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "user_ly")
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private Integer id;
    private String userName; // 密码将通过Jasypt加密
    private String password; // 密码将通过Jasypt加密
    private String role;
    private String email;
    private String phone;
}
