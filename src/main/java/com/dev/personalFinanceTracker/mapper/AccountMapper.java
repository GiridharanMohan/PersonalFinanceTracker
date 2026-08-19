package com.dev.personalFinanceTracker.mapper;

import com.dev.personalFinanceTracker.model.Account;
import com.dev.personalFinanceTracker.model.dto.AccountResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    @Mapping(target = "email", source = "user.email")
    AccountResponseDto mapAccountToAccountResponseDto(Account account);
}
