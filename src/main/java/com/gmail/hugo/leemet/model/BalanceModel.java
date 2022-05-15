package com.gmail.hugo.leemet.model;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Component;


@Component
public class BalanceModel {
    
    Account addingBalances(Account account, AccountInput accountIn) {
        List<Balance> balances = account.getBalances(); 
        for (Currency c : accountIn.getCurrencies()) {
            Balance b = new Balance();
            b.setAccountId(account.getAccountId());
            b.setCurrency(c);
            b.setAmount(BigDecimal.ZERO);
            balances.add(b);
        }
        account.setBalances(balances);
        return account;
    }
    
    
}
