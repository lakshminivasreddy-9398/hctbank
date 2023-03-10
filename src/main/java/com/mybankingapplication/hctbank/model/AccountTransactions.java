package com.mybankingapplication.hctbank.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Data
@Entity
public class AccountTransactions {
    @Id
    @Column(name ="transaction_id")
    private long transactionId;
    @Column(name = "transaction_ref_id",nullable = false)
    private long transactionReferenceId;
    @Column(name = "account_No")
    private long accountNo;
    private double credit;
    private double debit;
    @Column(name = "available_balance ")
    private double avvBalance;
    private Timestamp lastUpdated;
}
