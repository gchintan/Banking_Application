package com.chintan.banking_app.service;

import com.chintan.banking_app.dto.AccountDTO;

import java.util.List;


public interface AccountService {
    AccountDTO createAccount(AccountDTO accountDTO);
    AccountDTO getAccountByID(Long id);
    AccountDTO deposit(Long id, double amount);
    AccountDTO withdraw(Long id, double amount);
    List<AccountDTO> getAllAccounts();
    void deleteAccount(Long id);
}
