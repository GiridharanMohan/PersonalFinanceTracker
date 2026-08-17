package com.dev.personalFinanceTracker.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users", schema = "Personal_Finance_Tracker")
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @NotBlank(message = "Email is required")
    @Email(message = "Not a valid email ID")
    private String email;

    private String password;

    @NotBlank(message = "please specify the currency")
    private String currency;
}
