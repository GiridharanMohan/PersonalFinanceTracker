package com.dev.personalFinanceTracker.service;

import com.dev.personalFinanceTracker.model.dto.TransactionRequestDto;
import com.dev.personalFinanceTracker.model.dto.TransactionResponseDto;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    public String createTransaction(TransactionRequestDto transactionRequestDto) {
    }

    public Page<TransactionResponseDto> getMonthlySummary(int month, int year, int page, int size) {
    }

    public List<TransactionResponseDto> getFinanceBreakdown(int month, int year, int page, int size) {
    }

    public String deleteTransaction(long id) {
    }

    public Page<TransactionResponseDto> getAllTransaction() {
        return null;
    }
}
