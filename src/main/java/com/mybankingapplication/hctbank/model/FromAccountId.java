package com.mybankingapplication.hctbank.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "From_account_Id")
public class FromAccountId {
    @Id
    @Column(name = "transaction_id")
    private Long TransactionId;
    @Column(name = "Reference_id")
    private Long TransactionReferenceId;
    @Column(name = "From_account_number")
    private  Long AccountNo;
    private double credit;
    private double debit;
    private double availableBalance;

}
