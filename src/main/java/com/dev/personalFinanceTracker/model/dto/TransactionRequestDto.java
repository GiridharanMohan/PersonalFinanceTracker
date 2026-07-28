package com.dev.personalFinanceTracker.model.dto;

import com.dev.personalFinanceTracker.model.TransactionType;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class TransactionRequestDto {

    @NotBlank(message = "Name is required")
    private String expenseName;

    @NotBlank(message = "Please specify a type")
    private TransactionType type;

    private BigDecimal amount;
}
