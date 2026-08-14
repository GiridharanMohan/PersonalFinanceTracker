package com.dev.personalFinanceTracker.model.dto;

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
    private String type;
    private BigDecimal amount;
    private LocalDateTime timestamp;
}
