package com.dev.personalFinanceTracker.controller;

import com.dev.personalFinanceTracker.model.dto.ResponseDto;
import com.dev.personalFinanceTracker.model.dto.TransactionRequestDto;
import com.dev.personalFinanceTracker.model.dto.TransactionResponseDto;
import com.dev.personalFinanceTracker.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping(path = "/add-transaction")
    public ResponseEntity<ResponseDto<String>> createTransaction(@Valid @RequestBody TransactionRequestDto transactionRequestDto){
        return new ResponseEntity<>(transactionService.createTransaction(transactionRequestDto), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/delete-transaction/{id}")
    public ResponseEntity<ResponseDto<String>> deleteTransaction(@PathVariable long id){
        return new ResponseEntity<>(transactionService.deleteTransaction(id), HttpStatus.OK);
    }

    @GetMapping(path = "/fetch-transactions")
    public ResponseEntity<Page<TransactionResponseDto>> getAllTransaction(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size){
        return new ResponseEntity<>(transactionService.getAllTransaction(page, size), HttpStatus.OK);
    }

//    public ResponseEntity<Page<TransactionResponseDto>> getAllTransactionByFilter(@RequestParam long id, @RequestParam String type, @RequestParam String expenseName, @RequestParam)
}
