package com.dev.personalFinanceTracker.service;

import com.dev.personalFinanceTracker.model.User;
import com.dev.personalFinanceTracker.model.dto.ResponseDto;
import com.dev.personalFinanceTracker.model.dto.UserRequestDto;
import com.dev.personalFinanceTracker.repository.UserRepository;
import com.dev.personalFinanceTracker.util.Constant;
import com.dev.personalFinanceTracker.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseDto<String> userLogin(UserRequestDto user) {

        String email = user.getEmail();
        User requestedUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException(Constant.INVALID_CREDENTIALS));

        if(!passwordEncoder.matches(user.getPassword(), requestedUser.getPassword())) {
            log.error("Invalid email or password. Email: {}", email);
            throw new UsernameNotFoundException(Constant.INVALID_CREDENTIALS);
        }

        return new ResponseDto<>(
                true,
                null,
                jwtUtil.generateToken(email));
    }

    public ResponseDto<String> userSignUp(User user) {
        String email = user.getEmail();
        if(userRepository.findByEmail(email).isPresent()) {
            log.error("User already exists. Email: {}", email);
            throw new RuntimeException("User already exists. Please login!");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        return new ResponseDto<>(
                true,
                null,
                "Successfully registered"
                );
    }
}
