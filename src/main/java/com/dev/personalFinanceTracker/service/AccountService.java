package com.dev.personalFinanceTracker.service;

import com.dev.personalFinanceTracker.model.Account;
import com.dev.personalFinanceTracker.model.dto.AccountRequestDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    public String createAccount(@Valid Account account) {
    }

    public HttpStatusCode showAccount(long id, HttpStatus httpStatus) {
    }

    public HttpStatusCode editAccount(@Valid AccountRequestDto accountRequestDto, HttpStatus httpStatus) {
    }

    public HttpStatusCode deleteAccount(long id, HttpStatus httpStatus) {
    }
}
