package com.faithful.social_Media_App.controller;

import com.faithful.social_Media_App.Service.UserService;
import com.faithful.social_Media_App.model.User;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService){this.userService = userService;}
    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        return  new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

}
