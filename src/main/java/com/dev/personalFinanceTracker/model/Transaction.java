package com.dev.personalFinanceTracker.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Data
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account")
    private Account account;

    @Column(name = "transaction_name", nullable = false)
    private String transactionName;

    //type refers to income/expense.
    @Column(name = "transaction_type", nullable = false)
    private TransactionType transactionType;

    //category refers to groups like food, trip, bill, etc.
    @Column(name = "category", nullable = false)
    private Category transactionCategory;

    private BigDecimal amount;

    private LocalDateTime timestamp;
}
