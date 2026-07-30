package com.dev.personalFinanceTracker.service;

import com.dev.personalFinanceTracker.model.User;
import com.dev.personalFinanceTracker.model.dto.UserRequestDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public String userLogin(UserRequestDto user) {
    }

    public String userSignUp(User user) {
        return null;
    }
}
