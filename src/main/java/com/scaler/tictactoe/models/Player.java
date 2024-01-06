package com.scaler.tictactoe.models;

import com.scaler.tictactoe.models.enums.PlayerType;

public class Player {
    private int id;
    private String name;
    private char symbol;
    private PlayerType playerType;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public char getSymbol() {
        return symbol;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }
}
