package com.mybankingapplication.hctbank.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "login_details")
public class Credentials {

    @Id
    @Column(name = "customer_id")
    private Long custId;
    @Column(name = "password")
    private String password;
}
