package com.dev.personalFinanceTracker.model.dto;

import com.dev.personalFinanceTracker.model.Category;
import com.dev.personalFinanceTracker.model.TransactionType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class TransactionResponseDto {

    private Long id;
    private Long accountId;
    private String name;
    private TransactionType type;
    private Category category;
    private BigDecimal amount;
    private LocalDateTime timestamp;
}
