package com.mybankingapplication.hctbank.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;


@Data
@Entity public class CustomerDetails {
    @Id
    @Column(name = "Customer_id",nullable = false)
    private Long customerId;
    @Column(name = "customer_name",nullable = false)
    private String name;
    @Column(name = "address_id",nullable = false)
    private Long addressId;
    @Column(name = "phone",nullable = false)
    private Long phone;
    @Column(name = "email_id",nullable = false)
    private String email;
    @Column(name = "created",nullable = false)
    private Timestamp created;
    @Column(name = "last_Updated",nullable = false)
    private Timestamp lastUpdated;


}
