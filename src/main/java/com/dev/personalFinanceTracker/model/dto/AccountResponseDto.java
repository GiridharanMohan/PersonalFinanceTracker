package com.dev.personalFinanceTracker.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class AccountResponseDto {

    private Long id;
    private String name;
    private String email;
    private BigDecimal balance;
}
