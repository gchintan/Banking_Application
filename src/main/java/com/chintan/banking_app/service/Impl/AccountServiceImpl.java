package com.chintan.banking_app.service.Impl;

import com.chintan.banking_app.dto.AccountDTO;
import com.chintan.banking_app.mapper.AccountMapper;
import com.chintan.banking_app.model.Account;
import com.chintan.banking_app.repository.AccountRepository;
import com.chintan.banking_app.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
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

    @Override
    public AccountDTO getAccountByID(Long id) {
        Account account=accountRepository.findById(id).orElseThrow(()->new RuntimeException("Account does not exist"));
        return AccountMapper.mapToAccountDTO(account);

    }

    @Override
    public AccountDTO deposit(Long id, double amount) {
        Account account=accountRepository.findById(id).orElseThrow(()->new RuntimeException("Account does not exist"));
        double total=account.getBalance()+amount;
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDTO(savedAccount);

    }

    @Override
    public AccountDTO withdraw(Long id, double amount) {
        Account account=accountRepository.findById(id).orElseThrow(()->new RuntimeException("Account does not exist"));
        if(account.getBalance()<amount){
            throw new RuntimeException("Insufficient Balance");
        }
        double total=account.getBalance()-amount;
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDTO(savedAccount);

    }

    @Override
    public List<AccountDTO> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream().map((account)->AccountMapper.mapToAccountDTO(account)).collect(Collectors.toList());

    }

    @Override
    public void deleteAccount(Long id) {
        Account account=accountRepository.findById(id).orElseThrow(()->new RuntimeException("Account does not exist"));
        accountRepository.deleteById(id);

    }
}
