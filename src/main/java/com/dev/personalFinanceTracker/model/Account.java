package com.dev.personalFinanceTracker.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "accounts", schema = "Personal_Finance_Tracker")
@Data
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long id;

    @OneToOne
    @JoinColumn(name= "user")
    private User user;

    @NotBlank(message = "Name is required")
    private String name;

    private BigDecimal balance;
}
