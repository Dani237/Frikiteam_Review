package com.frikiteam.review.service;

import com.frikiteam.review.domain.model.User;
import com.frikiteam.review.domain.repositories.UserRepository;
import com.frikiteam.review.domain.service.UserService;
import com.frikiteam.review.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long userId, User user) {
        return userRepository.findById(userId).map(
                User -> {
                    User.setEmail(user.getEmail());
                    User.setFirstName(user.getFirstName());
                    User.setLastName(user.getLastName());
                    User.setLogo(user.getLogo());
                    User.setPassword(user.getPassword());
                    return userRepository.save(User);
                }
        ).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
    }

    @Override
    public User getUserByiD(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
    }

    @Override
    public ResponseEntity<?> deleteUser(Long id) {
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
