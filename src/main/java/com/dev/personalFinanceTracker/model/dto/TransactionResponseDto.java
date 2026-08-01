package com.dev.personalFinanceTracker.model.dto;

import com.dev.personalFinanceTracker.model.TransactionType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class TransactionResponseDto {

    private Long id;
    private String name;
    private TransactionType type;
    private String amount;
    private LocalDateTime timestamp;
}
