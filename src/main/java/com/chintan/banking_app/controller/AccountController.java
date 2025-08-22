package com.chintan.banking_app.controller;

import com.chintan.banking_app.dto.AccountDTO;
import com.chintan.banking_app.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }
    @PostMapping
    public ResponseEntity<AccountDTO> addAccount(@RequestBody AccountDTO accountDTO){
        return new ResponseEntity<>(accountService.createAccount(accountDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}/account")
    public ResponseEntity<AccountDTO> getAccount(@PathVariable Long id){
        return new ResponseEntity<>(accountService.getAccountByID(id),HttpStatus.OK);
    }

    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDTO> deposit(@PathVariable Long id,@RequestBody Map<String,Double> request){
        AccountDTO accountDTO=accountService.deposit(id, request.get("amount"));
        return ResponseEntity.ok(accountDTO);
    }

    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDTO> withdraw(@PathVariable Long id,@RequestBody Map<String,Double> request){
        AccountDTO accountDTO=accountService.withdraw(id, request.get("amount"));
        return ResponseEntity.ok(accountDTO);
    }

    @GetMapping("/allAccounts")
    public ResponseEntity<List<AccountDTO>> getAllAccounts(){
        List<AccountDTO> accounts = accountService.getAllAccounts();
        return new ResponseEntity<>(accounts,HttpStatus.OK);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id){
        accountService.deleteAccount(id);
        return ResponseEntity.ok("Account Deleted Successfully");
    }








}
