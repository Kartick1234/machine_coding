package com.scaler.tictactoe.exception;

public class InvalidNumberOfPlayersException extends Exception{
    public InvalidNumberOfPlayersException() {
    }

    public InvalidNumberOfPlayersException(String message) {
        super(message);
    }
}
