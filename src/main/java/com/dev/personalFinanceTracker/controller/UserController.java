package com.dev.personalFinanceTracker.controller;

import com.dev.personalFinanceTracker.model.User;
import com.dev.personalFinanceTracker.model.dto.UserRequestDto;
import com.dev.personalFinanceTracker.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(path = "/login")
    public ResponseEntity<String> login(@Valid @RequestBody UserRequestDto user){
        return new ResponseEntity<>(userService.userLogin(user), HttpStatus.ACCEPTED);
    }

    @PostMapping(path = "/sign-up")
    public ResponseEntity<String> signUp(@Valid @RequestBody User user){
        return new ResponseEntity<>(userService.userSignUp(user), HttpStatus.CREATED);
    }

}
