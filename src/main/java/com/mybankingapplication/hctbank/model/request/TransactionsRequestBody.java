package com.mybankingapplication.hctbank.model.request;

import lombok.Data;

@Data
public class TransactionsRequestBody {
    private Long fromAccountNo;
    private Long toAccountNo;
    private String type;
    private Double amount;

}
