package com.scaler.tictactoe.models;

import com.scaler.tictactoe.models.enums.CellState;
import com.scaler.tictactoe.models.enums.PlayerType;

import java.util.Scanner;

public class Player {
    private int id;
    private String name;
    private char symbol;
    private PlayerType playerType;

    public Move makeMove(Board board) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the row for your move");
        int row = sc.nextInt();
        System.out.println("Enter the column for your move");
        int col = sc.nextInt();
        //To-do: Validation of the move-check row and col
        board.getBoard().get(row).get(col).setCellState(CellState.FILLED);
        board.getBoard().get(row).get(col).setPlayer(this);
        return new Move(row, col, this);
    }

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
