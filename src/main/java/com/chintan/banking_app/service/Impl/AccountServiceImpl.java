package com.chintan.banking_app.service.Impl;

import com.chintan.banking_app.dto.AccountDTO;
import com.chintan.banking_app.mapper.AccountMapper;
import com.chintan.banking_app.model.Account;
import com.chintan.banking_app.repository.AccountRepository;
import com.chintan.banking_app.service.AccountService;

public class AccountServiceImpl implements AccountService {

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    private AccountRepository accountRepository;
    @Override
    public AccountDTO createAccount(AccountDTO accountDTO) {
        Account account= AccountMapper.mapToAccount(accountDTO);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDTO(savedAccount);

    }
}
