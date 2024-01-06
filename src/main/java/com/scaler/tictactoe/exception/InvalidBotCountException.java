package com.scaler.tictactoe.exception;

public class InvalidBotCountException extends Exception{
    public InvalidBotCountException() {
    }

    public InvalidBotCountException(String message) {
        super(message);
    }
}
