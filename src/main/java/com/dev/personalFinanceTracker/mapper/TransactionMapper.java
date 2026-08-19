package com.dev.personalFinanceTracker.mapper;

import com.dev.personalFinanceTracker.model.Account;
import com.dev.personalFinanceTracker.model.Transaction;
import com.dev.personalFinanceTracker.model.dto.TransactionRequestDto;
import com.dev.personalFinanceTracker.model.dto.TransactionResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring", imports = LocalDateTime.class)
public interface TransactionMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "account", source = "account")
    @Mapping(target = "timestamp", expression = "java(LocalDateTime.now())")
    Transaction mapTransactionRequestDtoToTransaction(TransactionRequestDto transactionRequestDto, Account account);

    @Mapping(target = "accountId", source = "account.id")
    @Mapping(target = "name", source = "transactionName")
    @Mapping(target = "type", source = "transactionType")
    @Mapping(target = "category", source = "transactionCategory")
    TransactionResponseDto mapTransactionToTransactionResponseDto(Transaction transaction);
}
