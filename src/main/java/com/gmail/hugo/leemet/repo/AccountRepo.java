package com.gmail.hugo.leemet.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gmail.hugo.leemet.model.Account;

@Repository
public interface AccountRepo extends JpaRepository<Account, Long> {
    
}
