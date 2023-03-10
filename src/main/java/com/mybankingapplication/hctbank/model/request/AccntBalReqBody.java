package com.mybankingapplication.hctbank.model.request;

import lombok.Data;

@Data
public class AccntBalReqBody {
    private Long accountId;
    private Double  balance;

}
