package com.gmail.hugo.leemet.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gmail.hugo.leemet.model.Transaction;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Long> {
    
}
