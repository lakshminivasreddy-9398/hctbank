package com.mybankingapplication.hctbank.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "cust_to_acc_map")
@AllArgsConstructor
@NoArgsConstructor
public class CustToAccMap {
    @Id
    @Column(name = "account_id")
    private Long accountNo;
    @Column(name ="Customer_id" )
    private Long customerId;
}
