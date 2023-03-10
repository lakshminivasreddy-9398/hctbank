package com.mybankingapplication.hctbank.model.request;

import lombok.Data;

@Data
public class CredentialsRequestBody {
    private Long customerId;
    private String password;
}
