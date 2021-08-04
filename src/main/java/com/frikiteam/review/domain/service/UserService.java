package com.frikiteam.review.domain.service;

import com.frikiteam.review.domain.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    User createUser(User user);
    User updateUser(Long userId, User user);
    User getUserByiD(Long id);
    ResponseEntity<?> deleteUser(Long id);
    List<User> getAllUsers();

}
