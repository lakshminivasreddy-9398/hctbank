package com.mybankingapplication.hctbank.model.request;

import lombok.Data;

@Data
public class TransactionsRequestBody {
    private Long acc_id;
    private Long to_acc_id;
    private String type;
    private Double amount;

}
