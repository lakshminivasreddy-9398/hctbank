package com.mybankingapplication.hctbank.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class AccountBalance {
    @Id
    @Column(name = "Account_no")
    private Long accountNo;
    @Column(name = "Account_bal")
    private Double balance;
}
