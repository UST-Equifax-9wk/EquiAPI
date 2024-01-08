package com.revature.ecommerce.exceptions;

public class UserDoesNotExistException extends Exception{
    public UserDoesNotExistException(String msg){
        super(msg);
    }
}
