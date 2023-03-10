package com.mybankingapplication.hctbank.repository;

import com.mybankingapplication.hctbank.model.AccountBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface AccountBalanceRepository extends JpaRepository<AccountBalance,Long> {
    @Query("select balance from AccountBalance where accountNo = ?1")
    public Integer findBalanceByAccountNo(Long accountNo);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update AccountBalance set balance = balance+?2 where accountNo = ?1")
    public Integer creditBalanceByAccountNo(long accountNo, double balance);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update AccountBalance set balance = balance-?2 where accountNo = ?1")
    public Integer debitBalanceByAccountNo(long accountNo, double balance);

}
