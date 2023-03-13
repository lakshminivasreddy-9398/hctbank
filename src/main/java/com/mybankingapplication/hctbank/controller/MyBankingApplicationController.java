package com.mybankingapplication.hctbank.controller;

import com.mybankingapplication.hctbank.Exceptions.InvalidInputExceptions;
import com.mybankingapplication.hctbank.model.AccountBalance;
import com.mybankingapplication.hctbank.model.AccountTransactions;
import com.mybankingapplication.hctbank.model.CustomerDetails;
import com.mybankingapplication.hctbank.model.request.CredentialsRequestBody;
import com.mybankingapplication.hctbank.model.request.CustomerDetailsRequestBody;
import com.mybankingapplication.hctbank.model.request.TransactionsRequestBody;
import com.mybankingapplication.hctbank.model.responce.IResponse;
import com.mybankingapplication.hctbank.repository.AccountBalanceRepository;
import com.mybankingapplication.hctbank.repository.AccountTransactionsRepository;
import com.mybankingapplication.hctbank.service.IMyBankService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class MyBankingApplicationController {

    private IMyBankService iMyBankService;
    private CustomerDetailsRequestBody requestBody;
    private AccountBalanceRepository accountBalanceRepository;
    private AccountTransactionsRepository accountTransactionsRepository;

    public MyBankingApplicationController(IMyBankService iMyBankService)
    {
        this.iMyBankService = iMyBankService;
    }


    @GetMapping("/wish")
    public String wish()
    {
        return "hello";
    }
    @PostMapping("/customers")
    public ResponseEntity<IResponse> saveCustomerDetails
            (@RequestBody CustomerDetailsRequestBody customerDetailsRequestBody)
    {
        IResponse iResponse= iMyBankService.saveCustomerDetails(customerDetailsRequestBody);
        return new ResponseEntity<IResponse>(iResponse,HttpStatus.CREATED);
    }
    @PostMapping("/password")
    public ResponseEntity<String> updatePassword
            (@RequestBody CredentialsRequestBody credentialsRequestBody)
    {
        String response=iMyBankService.createPassword(credentialsRequestBody);
        return new ResponseEntity<String>((response != null ? response:new InvalidInputExceptions
                ("Invalid Password",HttpStatus.BAD_REQUEST.value())).toString(),HttpStatus.CREATED);
    }
    @GetMapping("/customers")
    public Object getAllCustomers(@RequestParam(name = "customerId",required = false)Long customerId)
    {
        Object response=iMyBankService.getCustomerDetails(customerId);

        return response;
    }
    @GetMapping("/balance")
    public ResponseEntity<String> getAccountBalance(@RequestParam(name = "accNo") long accountNo,Long customerId){
        Object accountBalance= iMyBankService.getAccountBalance(accountNo,customerId);

        return new ResponseEntity<String>(accountBalance!=null?"Account balance is: "
                +accountBalance:"please provide valid accountNo",HttpStatus.OK);
    }
    @PostMapping("/transactions")
    public ResponseEntity<String> savaTransactions
            (@RequestBody TransactionsRequestBody transactionsRequestBody)
    {
        String response=iMyBankService.saveAccountTransactions(transactionsRequestBody);
        return new ResponseEntity<String>((response != null ? response : new InvalidInputExceptions
                ("Invalid Details", HttpStatus.BAD_REQUEST.value())).toString(), HttpStatus.CREATED);

    }@GetMapping("/transactionsDetails")
    public Object getAllDataByAccountNo(@RequestParam(name = "accountNo")Long accountNo,
                                        @RequestParam(name = "transactionId") Long transactionId)
    {
        Object response=iMyBankService.getAllDataByAccountNo(accountNo,transactionId);
                return response;

    }


}
