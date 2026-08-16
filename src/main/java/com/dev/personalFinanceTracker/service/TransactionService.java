package com.dev.personalFinanceTracker.service;

import com.dev.personalFinanceTracker.model.*;
import com.dev.personalFinanceTracker.model.dto.ResponseDto;
import com.dev.personalFinanceTracker.model.dto.TransactionRequestDto;
import com.dev.personalFinanceTracker.model.dto.TransactionResponseDto;
import com.dev.personalFinanceTracker.repository.AccountRepository;
import com.dev.personalFinanceTracker.repository.CategoryRepository;
import com.dev.personalFinanceTracker.repository.TransactionRepository;
import com.dev.personalFinanceTracker.util.Constant;
import com.dev.personalFinanceTracker.util.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TransactionService {

    @Autowired
    private Util util;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public ResponseDto<String> createTransaction(TransactionRequestDto transactionRequestDto) {
        User user = util.getCurrentUser();
        Account account = accountRepository.findByUserId(user.getId())
                .orElseThrow(() -> new RuntimeException(Constant.ACCOUNT_NOT_FOUND));

        Category categoryEntity = new Category();
        categoryEntity.setType(transactionRequestDto.getType());
        categoryEntity.setName(transactionRequestDto.getExpenseName());
        categoryEntity = categoryRepository.save(categoryEntity);
        Transaction transactionEntity = new Transaction();
        transactionEntity.setAccount(account);
        transactionEntity.setCategory(categoryEntity);
        transactionEntity.setAmount(transactionRequestDto.getAmount());
        transactionEntity.setTimestamp(LocalDateTime.now());
        transactionRepository.save(transactionEntity);
        return new ResponseDto<>(true, null, "Successfully saved transaction");
    }

    public Page<TransactionResponseDto> getMonthlySummary(int month, int year, int page, int size) {
        User currentUser = util.getCurrentUser();
        Account userAccount = accountRepository.findByUserId(currentUser.getId())
                .orElseThrow(() -> new RuntimeException(Constant.ACCOUNT_NOT_FOUND));

        LocalDate startDate = YearMonth.of(year, month).atDay(1);
        LocalDate endDate = YearMonth.of(year, month).atEndOfMonth();
        Page<Transaction> responseEntities = transactionRepository.getAllTransactionsByMonthAndYear(
                                                                    startDate,
                                                                    endDate,
                                                                    PageRequest.of(--page, size));

        Page<TransactionResponseDto> response = responseEntities.map(transaction -> {
            TransactionResponseDto responseEntity = new TransactionResponseDto();
            responseEntity.setId(transaction.getId());
            responseEntity.setAccountId(userAccount.getId());
            responseEntity.setName(transaction.getCategory().getName());
            responseEntity.setType(transaction.getCategory().getType());
            responseEntity.setAmount(transaction.getAmount());
            responseEntity.setTimestamp(transaction.getTimestamp());
            return responseEntity;
        });

        return response;
    }

    public ResponseDto<Map<String, Double>> getFinanceBreakdownByMonth(int month, int year) {
        User currentUser = util.getCurrentUser();
        Account userAccount = accountRepository.findByUserId(currentUser.getId())
                .orElseThrow(() -> new RuntimeException(Constant.ACCOUNT_NOT_FOUND));

        String monthOfYear = YearMonth.of(year, month).format(DateTimeFormatter.ofPattern("yyyy-MM"));
        List<Transaction> transactionEntities = transactionRepository.findAllTransactionsByAccountIdAndYearMonth(userAccount.getId(), monthOfYear);
        log.info("Fetching Transactions from the Account ID: {}, Year and Month: {}", userAccount.getId(), monthOfYear);

        Map<String, Double> groupedTransactions = transactionEntities.stream()
                .filter(entity -> TransactionType.EXPENSE.name().equals(entity.getCategory().getType()))
                .collect(Collectors.groupingBy(t -> t.getCategory().getName(),
                        Collectors.summingDouble(t -> t.getAmount().doubleValue())));

        return new ResponseDto<>(true, null, groupedTransactions);
    }

    public ResponseDto<String> deleteTransaction(long id) {
        User user = util.getCurrentUser();
        Account account = accountRepository.findByUserId(user.getId())
                .orElseThrow(() -> new RuntimeException(Constant.ACCOUNT_NOT_FOUND));

        Long accountId = transactionRepository.findAccountIdByTransactionId(id)
                .orElseThrow(() -> new RuntimeException(Constant.TRANSACTION_NOT_FOUND));

        if(accountId != account.getId())
            throw new RuntimeException(Constant.TRANSACTION_NOT_FOUND);

        transactionRepository.deleteById(id);
        return new ResponseDto<>(true, null, "Transaction deleted successfully");
    }

    public Page<TransactionResponseDto> getAllTransaction(int page, int size) {
        User user = util.getCurrentUser();
        Account userAccount = accountRepository.findByUserId(user.getId())
                .orElseThrow(() -> new RuntimeException(Constant.ACCOUNT_NOT_FOUND));

        Pageable pageable = PageRequest.of(--page, size);
        Page<Transaction> transactions = transactionRepository.findAllTransactionsByAccountId(
                userAccount.getId(), pageable);
        //converting Transaction to TransactionDto manually for now.
        //Todo: use mapper
        Page<TransactionResponseDto> responseEntities = transactions.map(transaction -> {
            TransactionResponseDto responseEntity = new TransactionResponseDto();
            responseEntity.setId(transaction.getId());
            responseEntity.setAccountId(userAccount.getId());
            responseEntity.setName(transaction.getCategory().getName());
            responseEntity.setType(transaction.getCategory().getType());
            responseEntity.setAmount(transaction.getAmount());
            responseEntity.setTimestamp(transaction.getTimestamp());
            return responseEntity;
        });
        return responseEntities;
    }
}
