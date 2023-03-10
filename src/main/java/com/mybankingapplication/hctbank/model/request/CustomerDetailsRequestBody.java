package com.mybankingapplication.hctbank.model.request;

import lombok.Data;

@Data
public class CustomerDetailsRequestBody {
    private String name;
    private Long phone;
    private String email;
    private CustomerAddressRequestBody address;


}
