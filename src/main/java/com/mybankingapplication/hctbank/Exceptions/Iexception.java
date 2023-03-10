package com.mybankingapplication.hctbank.Exceptions;

import com.mybankingapplication.hctbank.model.responce.IResponse;

public interface Iexception  extends IResponse {
    void setMessage(String message, int status);
}
