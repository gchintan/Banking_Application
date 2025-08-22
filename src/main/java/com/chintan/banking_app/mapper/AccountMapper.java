package com.chintan.banking_app.mapper;

import com.chintan.banking_app.dto.AccountDTO;
import com.chintan.banking_app.model.Account;

public class AccountMapper {

    public static Account mapToAccount(AccountDTO accountDTO){
        Account account = new Account(accountDTO.getId(), accountDTO.getAccountHolderName(), accountDTO.getBalance());
        return account;
    }

    public static AccountDTO mapToAccountDTO(Account account){
        AccountDTO accountDto=new AccountDTO(account.getId(), account.getAccountHolderName(), account.getBalance());
        return accountDto;
    }
}
