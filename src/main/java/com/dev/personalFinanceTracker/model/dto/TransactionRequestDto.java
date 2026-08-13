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

    @NotNull(message = "amount cannot be null")
    @DecimalMin(value = "0.01", message = "amount should be greater than zero")
    @Digits(integer = 10, fraction = 2)
    private BigDecimal amount;
}
