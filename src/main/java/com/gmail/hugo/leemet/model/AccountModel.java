package com.gmail.hugo.leemet.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gmail.hugo.leemet.repo.AccountRepo;

@Component
public class AccountModel {
    
    @Autowired
    AccountRepo accountRepo;
    
    @Autowired
    BalanceModel balanceModel;
    
   public Account createAccount(AccountInput accountIn) {
        Account account = new Account();
        accountRepo.save(account);
        account.setCustomerId(accountIn.getCustomerId());
        balanceModel.addingBalances(account, accountIn);
        accountRepo.save(account);
        return account;
    }
    
}
