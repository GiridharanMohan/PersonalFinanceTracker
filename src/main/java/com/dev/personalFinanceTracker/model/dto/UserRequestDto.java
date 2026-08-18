package com.dev.personalFinanceTracker.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRequestDto {

    @NotBlank(message = "Email is required")
    @Email(message = "Not a valid Email ID")
    private String email;

    @NotBlank(message = "Password is required")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,20}$",
            message = "Password must contain at least eight characters, one digit, one lowercase, one uppercase, and one special character")
    private String password;

    //Todo: use enum
    private String currency;
}
