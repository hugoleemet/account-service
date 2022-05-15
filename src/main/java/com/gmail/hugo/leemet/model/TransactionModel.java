package com.gmail.hugo.leemet.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gmail.hugo.leemet.repo.AccountRepo;
import com.gmail.hugo.leemet.repo.TransactionRepo;

@Component
public class TransactionModel {
    
    @Autowired
    TransactionRepo transactionRepo;
    
    @Autowired
    AccountRepo accountRepo;
    
    public List<Transaction> getTransactionsByAccountId(long id) {
        if (!accountRepo.existsById(id)) {
            throw new NoSuchElementException();
        }
        List<Transaction> allTransactions = transactionRepo.findAll();
        List<Transaction> result = new ArrayList<>();
        for (Transaction t : allTransactions) {
            if (t.getAccountId() == id) {
                result.add(t);
            }
        }
        return result;
    }
    
    public Transaction makeTransaction(Transaction transaction) {
        if (transaction.getAmount().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Invalid amount (negative amount not allowed)");
        }
        Balance balance = getBalance(transaction);
        Direction direction = transaction.getDirection();
        if (direction.equals(Direction.IN)) {
            balance.setAmount(balance.getAmount().add(transaction.getAmount()));
        } else if (direction.equals(Direction.OUT)) {
            BigDecimal subtraction = balance.getAmount().subtract(transaction.getAmount());
            if (subtraction.compareTo(BigDecimal.ZERO) >= 0) {
                balance.setAmount(balance.getAmount().subtract(transaction.getAmount()));
            } else {
                throw new IllegalArgumentException("Insufficient funds");
            }
        } 
        transaction.setRemainingBalance(balance.getAmount());
        return transaction;
    }
    
    private Balance getBalance(Transaction transaction) {
        Account account = accountRepo.getById(transaction.getAccountId());
      Currency currency = transaction.getCurrency();
      List<Balance> balances = account.getBalances();
      Balance balance = null;
      for (Balance b : balances) {
          if (b.getCurrency().equals(currency)) {
              balance = b;
          }
      }
      if (balance == null) {
          balance = new Balance();
          balance.setAccountId(account.getAccountId());
          balance.setCurrency(currency);
          balance.setAmount(BigDecimal.ZERO);
          balances.add(balance);
      }
      return balance;
    }
    
}
