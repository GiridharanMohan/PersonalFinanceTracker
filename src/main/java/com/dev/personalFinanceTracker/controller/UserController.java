package com.dev.personalFinanceTracker.controller;

import com.dev.personalFinanceTracker.model.User;
import com.dev.personalFinanceTracker.model.dto.ResponseDto;
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
    public ResponseEntity<ResponseDto<String>> login(@Valid @RequestBody UserRequestDto user){
        ResponseDto<String> responseDto = userService.userLogin(user);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PostMapping(path = "/sign-up")
    public ResponseEntity<ResponseDto<String>> signUp(@Valid @RequestBody User user){
        ResponseDto<String> responseDto = userService.userSignUp(user);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

}
