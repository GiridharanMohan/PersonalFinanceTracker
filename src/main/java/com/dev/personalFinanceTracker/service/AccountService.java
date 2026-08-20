package com.dev.personalFinanceTracker.service;

import com.dev.personalFinanceTracker.exception.DataNotFoundException;
import com.dev.personalFinanceTracker.mapper.AccountMapper;
import com.dev.personalFinanceTracker.model.Account;
import com.dev.personalFinanceTracker.model.User;
import com.dev.personalFinanceTracker.model.dto.AccountRequestDto;
import com.dev.personalFinanceTracker.model.dto.AccountResponseDto;
import com.dev.personalFinanceTracker.model.dto.ResponseDto;
import com.dev.personalFinanceTracker.repository.AccountRepository;
import com.dev.personalFinanceTracker.repository.TransactionRepository;
import com.dev.personalFinanceTracker.repository.UserRepository;
import com.dev.personalFinanceTracker.util.Constant;
import com.dev.personalFinanceTracker.util.Util;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Slf4j
@Service
public class AccountService {

    @Autowired
    private Util util;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    private AccountMapper accountMapper = Mappers.getMapper(AccountMapper.class);

    //currently 1 user can have 1 account
    //but actual plan is to make 1 person can have multiple accounts.
    public ResponseDto<String> createAccount(AccountRequestDto account) {
        User currentUser = util.getCurrentUser();
        if(accountRepository.findByUserId(currentUser.getId()).isPresent())
            throw new RuntimeException("Account already exists for the user");

        Account responseEntity = new Account();
        responseEntity.setUser(currentUser);
        responseEntity.setName(account.getName());
        responseEntity.setBalance(new BigDecimal(0));
        accountRepository.save(responseEntity);
        return new ResponseDto<>(true,null,"successfully saved account");
    }

    public ResponseDto<AccountResponseDto> showAccount() {
        User user = util.getCurrentUser();
        Account account = accountRepository.findByUserId(user.getId())
                .orElseThrow(() -> new DataNotFoundException("No account is linked with the user"));

        AccountResponseDto responseEntity = accountMapper.mapAccountToAccountResponseDto(account);
        return new ResponseDto<>(true, null, responseEntity);
    }

    public ResponseDto<String> editAccount(AccountRequestDto accountRequestDto) {
        User user = util.getCurrentUser();
        Account account = accountRepository.findByUserId(user.getId())
                .orElseThrow(() -> new DataNotFoundException(Constant.ACCOUNT_NOT_FOUND));

        account.setName(accountRequestDto.getName());
        accountRepository.save(account);
        return new ResponseDto<>(true, null, "Changes are done successfully");
    }

    @Transactional
    public ResponseDto<String> deleteAccount() {
        User user = util.getCurrentUser();
        Long accountId = accountRepository.findByUserId(user.getId())
                .orElseThrow(() -> new DataNotFoundException(Constant.ACCOUNT_NOT_FOUND))
                .getId();

        transactionRepository.deleteAllByAccountId(accountId);
        accountRepository.deleteById(accountId);
        return new ResponseDto<>(true, null, "Account deleted successfully");
    }
}
