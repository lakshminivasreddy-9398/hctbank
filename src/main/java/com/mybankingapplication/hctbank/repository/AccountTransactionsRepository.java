package com.mybankingapplication.hctbank.repository;

import com.mybankingapplication.hctbank.model.AccountTransactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountTransactionsRepository extends JpaRepository<AccountTransactions,Long> {
    @Query(value = "select * from account_transactions where account_no= ?1",nativeQuery = true)
    public List<AccountTransactions> findAllByAccountNo(Long accountNo);
    @Query(value = "select transaction_id from account_transactions where account_no= ?1 AND transaction_ref_id=2",nativeQuery = true)
    Long findTransactionId(Long accountNo,Long transactionReferenceId);
    @Query(value = "select * from account_transactions where transaction_ref_id= ?1",nativeQuery = true)
    List<AccountTransactions> findAllByTransactionRefID(Long transactionReferenceId);

}
