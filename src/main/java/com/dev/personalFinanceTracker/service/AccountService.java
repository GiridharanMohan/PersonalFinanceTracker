package com.dev.personalFinanceTracker.service;

import com.dev.personalFinanceTracker.model.Account;
import com.dev.personalFinanceTracker.model.dto.AccountRequestDto;
import com.dev.personalFinanceTracker.model.dto.AccountResponseDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    public String createAccount(Account account) {
    }

    public AccountResponseDto showAccount(long id) {
    }

    public String editAccount(AccountRequestDto accountRequestDto) {
    }

    public String deleteAccount(long id) {
        return null;
    }
}
