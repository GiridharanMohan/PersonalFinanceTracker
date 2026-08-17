package com.dev.personalFinanceTracker.service;

import com.dev.personalFinanceTracker.model.Account;
import com.dev.personalFinanceTracker.model.User;
import com.dev.personalFinanceTracker.model.dto.AccountRequestDto;
import com.dev.personalFinanceTracker.model.dto.AccountResponseDto;
import com.dev.personalFinanceTracker.model.dto.ResponseDto;
import com.dev.personalFinanceTracker.repository.AccountRepository;
import com.dev.personalFinanceTracker.repository.UserRepository;
import com.dev.personalFinanceTracker.util.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Slf4j
@Service
public class AccountService {

    @Autowired
    private Util util;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

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
        Optional<Account> account = accountRepository.findByUserId(user.getId());

        if (account.isEmpty())
            return new ResponseDto<>(false, "No account is linked with the user", null);

        Account myAccount = account.get();
        AccountResponseDto responseEntity = new AccountResponseDto();
        responseEntity.setId(myAccount.getId());
        responseEntity.setName(myAccount.getName());
        responseEntity.setEmail(myAccount.getUser().getEmail());
        responseEntity.setBalance(myAccount.getBalance());
        return new ResponseDto<>(true, null, responseEntity);
    }

    public ResponseDto<String> editAccount(AccountRequestDto accountRequestDto) {
        String msg = "Account does not exists";
        User user = util.getCurrentUser();

        Account account = accountRepository.findByUserId(user.getId())
                .orElseThrow(() -> new RuntimeException(msg));
        account.setName(accountRequestDto.getName());
        accountRepository.save(account);
        return new ResponseDto<>(true, null, "Changes are done successfully");
    }

    //Todo: when deleting an account, the associated transactions should also be deleted.
    public ResponseDto<String> deleteAccount() {
        String msg = "Account does not exists";
        try {
            User user = util.getCurrentUser();
            Account account = accountRepository.findByUserId(user.getId())
                    .orElseThrow(() -> new RuntimeException(msg));

            accountRepository.deleteById(account.getId());
            return new ResponseDto<>(true, null, "Account deleted successfully");
        } catch (Exception e) {
            log.error("Error occurred while deleting the account - {}", e.getMessage());
            return new ResponseDto<>(false, msg, null);
        }
    }
}
