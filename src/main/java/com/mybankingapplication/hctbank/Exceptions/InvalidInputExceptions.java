package com.mybankingapplication.hctbank.Exceptions;

public class InvalidInputExceptions implements Iexception{
    public String message = null;
    public int status = 0;

    public InvalidInputExceptions(String invalidPassword, int value) {

    }

    @Override
    public void setMessage(String message, int status) {
        this.message = message;
        this.status = status;


    }
}
