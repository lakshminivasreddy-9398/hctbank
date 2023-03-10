package com.mybankingapplication.hctbank.model.request;

import lombok.Data;

@Data
public class CustomerAddressRequestBody {

    private String country;
    private String city;
    private String addressLane;
    private Long pin;




}
