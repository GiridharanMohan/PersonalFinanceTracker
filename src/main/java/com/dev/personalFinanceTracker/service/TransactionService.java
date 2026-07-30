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
    public String createTransaction(@Valid TransactionRequestDto transactionRequestDto) {
    }

    public Page<TransactionResponseDto> getMonthlySummary(int month, int year, int page, int size) {
    }

    public List<TransactionResponseDto> getFinanceBreakdown(int month, int year, int page, int size) {
    }

    public HttpStatusCode deleteTransaction(long id, HttpStatus httpStatus) {
    }

    public Page<TransactionResponseDto> getAllTransaction() {
    }
}
