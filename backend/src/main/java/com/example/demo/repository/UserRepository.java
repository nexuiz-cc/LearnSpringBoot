package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(value = "SELECT * FROM user_ly WHERE user_name = :userName", nativeQuery = true)
    User findByUsername(@Param("userName") String userName);

    @Query(value = "SELECT * FROM user_ly", nativeQuery = true)
    List<User> findAllUsers();

    @Query(value ="SELECT * FROM user_ly ORDER BY id DESC LIMIT 1", nativeQuery = true)
    User findTopByOrderByIdDesc();
}