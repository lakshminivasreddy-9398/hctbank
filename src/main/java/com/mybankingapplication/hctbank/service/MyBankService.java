package com.mybankingapplication.hctbank.service;

import com.mybankingapplication.hctbank.model.*;
import com.mybankingapplication.hctbank.model.request.*;
import com.mybankingapplication.hctbank.model.responce.CreatCustDetaResopse;
import com.mybankingapplication.hctbank.model.responce.IResponse;
import com.mybankingapplication.hctbank.repository.*;
import com.mybankingapplication.hctbank.utils.IdGenerator;
import com.mybankingapplication.hctbank.utils.PasswordValidations;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
public class MyBankService implements IMyBankService {
    public static final  int CUSTOMER_ID_LENGTH=6;
    public static final int ADDRESS_ID_LENGTH=6;
    public static final int ACCOUNT_ID_LENGTH=12;
    private static final int TRANSACTION_ID_LENGTH=9;
    private static final int TRANSACTION_REFRID=4;
    private CustomerAddressRepository customerAddressRepository;
    private CustomerDetailsRepository customerDetailsRepository;
    private AccountBalanceRepository accountBalanceRepository;
    private AccIdToCustIDRepository accIdToCustIDRepository;
    private LoginDetailsRepository loginDetailsRepository;
    private AccountTransactionsRepository accountTransactionsRepository;


    public MyBankService(CustomerAddressRepository customerAddressRepository,
                         CustomerDetailsRepository customerDetailsRepository,
                         AccountBalanceRepository accountBalanceRepository,
                         LoginDetailsRepository loginDetailsRepository,
                         AccIdToCustIDRepository accIdToCustIDRepository,
                         AccountTransactionsRepository accountTransactionsRepository) {
        this.customerAddressRepository = customerAddressRepository;
        this.customerDetailsRepository = customerDetailsRepository;
        this.accountBalanceRepository = accountBalanceRepository;
        this.accIdToCustIDRepository=accIdToCustIDRepository;
        this.loginDetailsRepository=loginDetailsRepository;
        this.accountTransactionsRepository=accountTransactionsRepository;
    }

    Timestamp timestamp= Timestamp.from(Instant.now());

    @Override
    public IResponse saveCustomerDetails(CustomerDetailsRequestBody customerDetailsRequestBody)
    {
        Long addressId=saveCustomerAddress(customerDetailsRequestBody.getAddress());
        CustomerDetails customerDetails=new CustomerDetails();

        customerDetails.setCustomerId(IdGenerator.getId(CUSTOMER_ID_LENGTH));
        customerDetails.setName(customerDetailsRequestBody.getName());
        customerDetails.setAddressId(addressId);
        customerDetails.setPhone(customerDetailsRequestBody.getPhone());
        customerDetails.setEmail(customerDetailsRequestBody.getEmail());
        customerDetails.setLastUpdated(Timestamp.from(Instant.now()));
        customerDetails.setCreated(Timestamp.from(Instant.now()));
        Long Custid=customerDetailsRepository.save(customerDetails).getCustomerId();
        Long accountId=updateAccountBalance(null);
        //creating a new pull req

        boolean mapResult=CustIdToAccIdMap(new CustToAccMap(accountId,Custid));
        return mapResult?new CreatCustDetaResopse(Custid):null;
    }

    @Override
    public Long saveCustomerAddress(CustomerAddressRequestBody customerAddressRequestBody) {
        CustomerAddress customerAddress=new CustomerAddress();
        customerAddress.setAddressId(IdGenerator.getId(ADDRESS_ID_LENGTH));
        customerAddress.setAddressLane(customerAddressRequestBody.getAddressLane());
        customerAddress.setCountry(customerAddressRequestBody.getCountry());
        customerAddress.setCity(customerAddressRequestBody.getCity());
        customerAddress.setLastUpdated(Timestamp.from(Instant.now()));
        return customerAddressRepository.save(customerAddress).getAddressId();

    }

    @Override
    public Long updateAccountBalance(AccntBalReqBody accntBalReqBody) {
        AccountBalance accountBalance=new AccountBalance();
        if(accntBalReqBody==null)
        {
            accountBalance.setAccountNo(IdGenerator.getId(ACCOUNT_ID_LENGTH));
            accountBalance.setBalance(500.00);
        }else
        {
            accountBalance.setAccountNo(accntBalReqBody.getAccountId());
            accountBalance.setBalance(accntBalReqBody.getBalance());
        }
        return accountBalanceRepository.save(accountBalance).getAccountNo();
    }

    @Override
    public boolean CustIdToAccIdMap(CustToAccMap custToAccMap) {
        CustToAccMap result=new CustToAccMap();
                result= (CustToAccMap) accIdToCustIDRepository.save(custToAccMap);

        return result!=null?true:false;
    }

    @Override
    public String createPassword(CredentialsRequestBody credentialsRequestBody) {
        Credentials loginDetails=new Credentials();
        loginDetails.setCustId(credentialsRequestBody.getCustomerId());
        loginDetails.setPassword(credentialsRequestBody.getPassword());
        boolean result=PasswordValidations.isValidPassword(loginDetails.getPassword());

        if (result)
        {
            loginDetailsRepository.save(loginDetails);
            return ("password saved for  "+" "+loginDetails.getCustId()+"id");
        }
        else
        {
           return ("please provide valid password");
        }



        //String result=PasswordValidations.isValidPassword(loginDetails.setPassword(credentialsRequestBody.getPassword()));

       // return result?

//        new ResponseEntity<String>
//                ("password saved for id"+" "+loginDetails.getCustId()+"successfully",HttpStatus.CREATED)
    }
    @Override
    public Object getCustomerDetails(Long customerId) {
    if (customerId==null)
    {
        return customerDetailsRepository.findAll();
    }else
    {
        return customerDetailsRepository.findById(customerId);
    }
    }

    @Override
    public Object getAccountBalance(Long accountNo, Long customerId)
    {
        if (accountNo!=0&&customerId==0)
        {
            return accountBalanceRepository.findById(accountNo);
        }
        if (accountNo==0&&customerId!=0)
        {
            return accountBalanceRepository.findById(customerId);
        }
        if(accountNo!=0&&customerId!=0)
        {
            return accountBalanceRepository.findById(accountNo);
        }
        else{
            return "This AccountNo is not associated with the CustomerId";
        //return accountBalanceRepository.findById(accountNo).get().getBalance();
    }
   // @Override
//    public String saveAccountTransactions(TransactionsRequestBody )
//            {
//        Long fromAccNo=transactionsRequestBody.getFromAccountNo();
//        Long toAccNo=transactionsRequestBody.getToAccountNo();
//        double fromAccBalance=accountBalanceRepository.findBalanceByAccountNo(fromAccNo);
//        double toAccBalance=accountBalanceRepository.findBalanceByAccountNo(toAccNo);
//        AccountTransactions fromTransaction=new AccountTransactions();
//        AccountTransactions toTransactions=new AccountTransactions();
//        if(transactionsRequestBody.getType().equals("credit")
//                &&fromAccBalance>=transactionsRequestBody.getAmount())
//        {
//        fromTransaction.setTransactionId(IdGenerator.getId(TRANSACTION_ID_LENGTH));
//        fromTransaction.setTransactionReferenceId(IdGenerator.getId(TRANSACTION_REFRID));
//        fromTransaction.setAccountNo(fromAccNo);
//        fromTransaction.setCredit(0.00);
//        fromTransaction.setDebit(transactionsRequestBody.getAmount());
//        fromTransaction.setAvvBalance(fromAccBalance-transactionsRequestBody.getAmount());
//
//        fromTransaction.setLastUpdated(Timestamp.from(Instant.now()));
//        accountBalanceRepository.debitBalanceByAccountNo(fromAccNo,fromTransaction.getDebit());
//        accountTransactionsRepository.save(fromTransaction);
//
//        toTransactions.setTransactionId(IdGenerator.getId(TRANSACTION_ID_LENGTH));
//        toTransactions.setTransactionReferenceId(fromTransaction.getTransactionReferenceId());
//        toTransactions.setAccountNo(toAccNo);
//        toTransactions.setCredit(transactionsRequestBody.getAmount());
//        toTransactions.setDebit(0.00);
//        toTransactions.setAvvBalance(toAccBalance+transactionsRequestBody.getAmount());
//        toTransactions.setLastUpdated(Timestamp.from(Instant.now()));
//        accountBalanceRepository.creditBalanceByAccountNo(toAccNo,toTransactions.getCredit());
//        accountTransactionsRepository.save(toTransactions);
//            return "The reference Id for the above credit transaction is "
//                    + fromTransaction.getTransactionReferenceId();
//
//
//        }
//        else
//        {
//            return "Please provide a valid Debit Amount! Or Please give a valid 'type' i.e either CREDIT/DEBIT";
        }

    @Override
    public String saveAccountTransactions(TransactionsRequestBody transactionsRequestBody) {
        Long fromAccNo=transactionsRequestBody.getFromAccountNo();
        Long toAccNo=transactionsRequestBody.getToAccountNo();
        double fromAccBalance=accountBalanceRepository.findBalanceByAccountNo(fromAccNo);
        double toAccBalance=accountBalanceRepository.findBalanceByAccountNo(toAccNo);
        AccountTransactions fromTransaction=new AccountTransactions();
        AccountTransactions toTransactions=new AccountTransactions();


        if(transactionsRequestBody.getType().equals("credit")
                &&fromAccBalance>=transactionsRequestBody.getAmount())
        {
        fromTransaction.setTransactionId(IdGenerator.getId(TRANSACTION_ID_LENGTH));
        fromTransaction.setTransactionReferenceId(IdGenerator.getId(TRANSACTION_REFRID));
        fromTransaction.setAccountNo(fromAccNo);
        fromTransaction.setCredit(0.00);
        fromTransaction.setDebit(transactionsRequestBody.getAmount());
        fromTransaction.setAvvBalance(fromAccBalance-transactionsRequestBody.getAmount());

        fromTransaction.setLastUpdated(Timestamp.from(Instant.now()));
        accountBalanceRepository.debitBalanceByAccountNo(fromAccNo,fromTransaction.getDebit());
        accountTransactionsRepository.save(fromTransaction);

        toTransactions.setTransactionId(IdGenerator.getId(TRANSACTION_ID_LENGTH));
        toTransactions.setTransactionReferenceId(fromTransaction.getTransactionReferenceId());
        toTransactions.setAccountNo(toAccNo);
        toTransactions.setCredit(transactionsRequestBody.getAmount());
        toTransactions.setDebit(0.00);
        toTransactions.setAvvBalance(toAccBalance+transactionsRequestBody.getAmount());
        toTransactions.setLastUpdated(Timestamp.from(Instant.now()));
        accountBalanceRepository.creditBalanceByAccountNo(toAccNo,toTransactions.getCredit());
        accountTransactionsRepository.save(toTransactions);
            return "The reference Id for the above credit transaction is "
                    + fromTransaction.getTransactionReferenceId();


        }
        if (transactionsRequestBody.getType().equals("debit")
                &&fromAccBalance>=transactionsRequestBody.getAmount())
        {
        fromTransaction.setTransactionId(IdGenerator.getId(TRANSACTION_ID_LENGTH));
        fromTransaction.setTransactionReferenceId(IdGenerator.getId(TRANSACTION_REFRID));
        fromTransaction.setAccountNo(fromAccNo);
        fromTransaction.setCredit(0.00);
        fromTransaction.setDebit(transactionsRequestBody.getAmount());
        fromTransaction.setAvvBalance(fromAccBalance-transactionsRequestBody.getAmount());

        fromTransaction.setLastUpdated(Timestamp.from(Instant.now()));
        accountBalanceRepository.debitBalanceByAccountNo(fromAccNo,fromTransaction.getDebit());
        accountTransactionsRepository.save(fromTransaction);

        toTransactions.setTransactionId(IdGenerator.getId(TRANSACTION_ID_LENGTH));
        toTransactions.setTransactionReferenceId(fromTransaction.getTransactionReferenceId());
        toTransactions.setAccountNo(toAccNo);
        toTransactions.setCredit(transactionsRequestBody.getAmount());
        toTransactions.setDebit(0.00);
        toTransactions.setAvvBalance(toAccBalance+transactionsRequestBody.getAmount());
        toTransactions.setLastUpdated(Timestamp.from(Instant.now()));
        accountBalanceRepository.creditBalanceByAccountNo(toAccNo,toTransactions.getCredit());
        accountTransactionsRepository.save(toTransactions);
            return "The reference Id for the above debit transaction is "
                    + fromTransaction.getTransactionReferenceId();
        }
        else
        {
            return "Please provide a valid Debit Amount! Or Please give a valid 'type' i.e either CREDIT/DEBIT";
        }


    }

    @Override
    public Object getAllDataByAccountNo(Long accountNo,Long transactionId)
    {
        if(transactionId==0&&accountNo!=0)
        {
            return accountTransactionsRepository.findAllByAccountNo(accountNo);
        }
        if (transactionId!=0&&accountNo==0)
        {
            return accountTransactionsRepository.findAllByTransactionRefID(transactionId);
        }
        if (transactionId!=0&&accountNo!=0)
        {
            return accountTransactionsRepository.
                    findById(accountTransactionsRepository.findTransactionId(accountNo,transactionId));
        }
        return null;

    }

    }

    // return new ResponseEntity<IResponse>
        // ("transaction details saved and available balance is"+accountTransactions.getAvvBalance());







