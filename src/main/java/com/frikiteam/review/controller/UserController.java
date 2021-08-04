package com.frikiteam.review.controller;

import com.frikiteam.review.domain.model.User;
import com.frikiteam.review.domain.service.UserService;
import com.frikiteam.review.resource.SaveUserResource;
import com.frikiteam.review.resource.UserResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper mapper;


    @GetMapping("/users")
    public List<UserResource> getAllUsers() {
        return userService.getAllUsers()
                .stream()
                .map(
                        user -> mapper.map(user, UserResource.class)
                ).collect(Collectors.toList());
    }

    @PostMapping("users")
    public UserResource saveUser(@RequestBody SaveUserResource resource) {
        User user = convertToEntity(resource);
        return convertToResource(userService.createUser(user));
    }

    @PutMapping("/users/{id}")
    public UserResource updateUser(@PathVariable Long id, @RequestBody SaveUserResource resource) {
        User user = convertToEntity(resource);
        return convertToResource(userService.updateUser(id, user));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }


    private User convertToEntity(SaveUserResource resource) {
        return mapper.map(resource, User.class);
    }
    private UserResource convertToResource(User entity) {
        return mapper.map(entity, UserResource.class);
    }

}
