package com.mybankingapplication.hctbank.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Data
@Entity
public class CustomerAddress {
    @Id
    @Column(name = "address_id")
    private Long addressId;
    @Column(name = "country")
    private String country;
    @Column(name = "city")
    private String city;
    @Column(name = "address_lane")
    private String addressLane;
    @Column(name = "pin")
    private Long pin;
    @Column(name = "last_updated")
    private Timestamp lastUpdated;


}
