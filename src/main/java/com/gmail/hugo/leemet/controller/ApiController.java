package com.gmail.hugo.leemet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gmail.hugo.leemet.model.Account;
import com.gmail.hugo.leemet.model.AccountInput;
import com.gmail.hugo.leemet.model.AccountModel;
import com.gmail.hugo.leemet.model.Transaction;
import com.gmail.hugo.leemet.model.TransactionModel;
import com.gmail.hugo.leemet.repo.AccountRepo;
import com.gmail.hugo.leemet.repo.TransactionRepo;

@RestController
public class ApiController { 
    
    @Autowired
    AccountRepo accountRepo;
    
    @Autowired
    TransactionRepo transacionRepo;
    
    @Autowired
    TransactionModel transactionModel;
    
    @Autowired
    AccountModel accountModel;

    // Accounts
    @PostMapping(value = "/create/account")
    Account greateAccount(@RequestBody AccountInput accountIn) {
        return accountModel.createAccount(accountIn);
    }
    
    @GetMapping(value = "account/{id}")
    Account getAccount(@PathVariable("id") Long id) {
        return accountRepo.findById(id).orElseThrow();
    }
    
    // Transactions
    @PostMapping(value = "/create/transaction")
    Transaction greateTransacion(@RequestBody Transaction transaction) {
        String description = transaction.getDescription();
        if (description == null || description == "") {
            throw new IllegalArgumentException("Description missing");
        }
        transactionModel.makeTransaction(transaction);
        transacionRepo.save(transaction);
        return transaction;
    }
    
    @GetMapping(value = "/transactions/{id}")
    List<Transaction> getTransactions(@PathVariable long id) {
        return transactionModel.getTransactionsByAccountId(id);
    }
}
