package com.dev.personalFinanceTracker.service;

import com.dev.personalFinanceTracker.model.User;
import com.dev.personalFinanceTracker.model.dto.UserRequestDto;
import com.dev.personalFinanceTracker.repository.UserRepository;
import com.dev.personalFinanceTracker.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String userLogin(UserRequestDto user) {
        if(user != null && user.getEmail() != null){
            String email = user.getEmail();
            Optional<User> optionalUser = userRepository.findByEmail(email);
            if(optionalUser.isPresent() && passwordEncoder.matches(user.getPassword(), optionalUser.get().getPassword())){
                return jwtUtil.generateToken(email);
            }
            return "User is not available. Please sign up first.";
        }
        return "Not a valid email";
    }

    public String userSignUp(User user) {
        return null;
    }
}
