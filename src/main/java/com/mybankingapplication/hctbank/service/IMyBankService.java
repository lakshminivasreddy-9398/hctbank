package com.mybankingapplication.hctbank.service;

import com.mybankingapplication.hctbank.model.CustToAccMap;
import com.mybankingapplication.hctbank.model.request.*;
import com.mybankingapplication.hctbank.model.responce.CreatCustDataResopse;

public interface IMyBankService {
    CreatCustDataResopse saveCustomerDetails(CustomerDetailsRequestBody customerDetailsRequestBody);
    Long saveCustomerAddress(CustomerAddressRequestBody customerAddressRequestBody);
    Long updateAccountBalance(AccntBalReqBody accntBalReqBody);
    boolean CustIdToAccIdMap(CustToAccMap custToAccMap);
    String createPassword(CredentialsRequestBody credentialsRequestBody);
    Object getCustomerDetails(Long customerId);
    Object getAccountBalance(Long accountNo, Long customerId);
    String saveAccountTransactions(TransactionsRequestBody transactionsRequestBody);
    Object getAllDataByAccountNo(Long accountNo, Long transactionId);

}
