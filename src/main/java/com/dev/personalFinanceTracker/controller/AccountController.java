package com.dev.personalFinanceTracker.controller;

import com.dev.personalFinanceTracker.model.Account;
import com.dev.personalFinanceTracker.model.dto.AccountRequestDto;
import com.dev.personalFinanceTracker.model.dto.AccountResponseDto;
import com.dev.personalFinanceTracker.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping(path = "/create-account")
    public ResponseEntity<String> createAccount(@Valid @RequestBody Account account){
        return new ResponseEntity<>(accountService.createAccount(account),HttpStatus.CREATED);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<AccountResponseDto> showAccount(@PathVariable long id){
        return new ResponseEntity<>(accountService.showAccount(id, HttpStatus.ACCEPTED));
    }

    @PutMapping(path = "/edit-account")
    public ResponseEntity<String> editAccount(@Valid @RequestBody AccountRequestDto accountRequestDto){
        return new ResponseEntity<>(accountService.editAccount(accountRequestDto, HttpStatus.ACCEPTED));
    }

    @DeleteMapping(path = "/delete-account/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable long id){
        return new ResponseEntity<>(accountService.deleteAccount(id, HttpStatus.ACCEPTED));
    }
}
