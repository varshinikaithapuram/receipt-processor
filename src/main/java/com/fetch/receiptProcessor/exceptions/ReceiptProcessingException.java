package com.fetch.receiptProcessor.exceptions;

import java.util.UUID;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

public class ReceiptProcessingException extends RuntimeException {
	
    public ReceiptProcessingException(String message, Exception e) {
        super(message, e);
    }
}


