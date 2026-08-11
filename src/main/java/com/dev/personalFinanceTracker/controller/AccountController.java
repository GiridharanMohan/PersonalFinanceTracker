package com.dev.personalFinanceTracker.controller;

import com.dev.personalFinanceTracker.model.dto.AccountRequestDto;
import com.dev.personalFinanceTracker.model.dto.AccountResponseDto;
import com.dev.personalFinanceTracker.model.dto.ResponseDto;
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
    public ResponseEntity<ResponseDto<String>> createAccount(@Valid @RequestBody AccountRequestDto account){
        return new ResponseEntity<>(accountService.createAccount(account),HttpStatus.CREATED);
    }

    @GetMapping(path = "/myAccount")
    public ResponseEntity<ResponseDto<AccountResponseDto>> showAccount(){
        return new ResponseEntity<>(accountService.showAccount(), HttpStatus.OK);
    }

    @PutMapping(path = "/edit-account")
    public ResponseEntity<ResponseDto<String>> editAccount(@Valid @RequestBody AccountRequestDto accountRequestDto){
        return new ResponseEntity<>(accountService.editAccount(accountRequestDto), HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete-account")
    public ResponseEntity<ResponseDto<String>> deleteAccount(){
        return new ResponseEntity<>(accountService.deleteAccount(), HttpStatus.OK);
    }
}
