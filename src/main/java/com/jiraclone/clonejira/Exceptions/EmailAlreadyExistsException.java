package com.jiraclone.clonejira.Exceptions;

public class EmailAlreadyExistsException extends RuntimeException{
    public EmailAlreadyExistsException(String massage){
        super(massage);
    }
}
