package com.malsum.shoppingsessionservice.exceptions;

public class NotEnoughStockException extends RuntimeException{
    public NotEnoughStockException(String message) {
        super(message);
    }

    public NotEnoughStockException() {
    }
}
