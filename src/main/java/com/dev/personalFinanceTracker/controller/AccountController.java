package com.dev.personalFinanceTracker.controller;

import com.dev.personalFinanceTracker.model.Account;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(name = "api/v1/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping(name = "/create-account")
    public ResponseEntity<String> createAccount(@Valid @RequestBody Account account){
        return new ResponseEntity<>(accountService.createAccount(account),HttpStatus.CREATED);
    }

    @GetMapping(name = "/{id}")
    public ResponseEntity<AccountDto> showAccount(@PathVariable long id){
        return new ResponseEntity<>(accountService.showAccount(id, HttpStatus.ACCEPTED));
    }

    @PutMapping(name = "/edit-account")
    public ResponseEntity<String> editAccount(@Valid @RequestBody AccountDto accountDto){
        return new ResponseEntity<>(accountService.editAccount(accountDto, HttpStatus.ACCEPTED));
    }

    @DeleteMapping(name = "/delete-account/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable long id){
        return new ResponseEntity<>(accountService.deleteAccount(id, HttpStatus.ACCEPTED));
    }
}
