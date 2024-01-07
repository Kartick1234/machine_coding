package com.scaler.tictactoe.models;

import com.scaler.tictactoe.exception.GameOverException;
import com.scaler.tictactoe.models.enums.CellState;
import com.scaler.tictactoe.models.enums.PlayerType;

import java.util.Scanner;

public class Player {
    private int id;
    private String name;
    private char symbol;
    private PlayerType playerType;

    public Player(int id, String name, char symbol, PlayerType playerType) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
        this.playerType = playerType;
    }

    public Move makeMove(Board board) throws GameOverException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the row for your move: " + this.getName());
        int row = sc.nextInt();
        System.out.println("Enter the column for your move: " + this.getName());
        int col = sc.nextInt();
        //To-do: Validation of the move-check row and col
        if(board.getBoard().get(row).get(col).getCellState().equals(CellState.FILLED)){
            System.out.println("The entered move is already filled, Please enter correct move");
            System.out.println("Enter the row for your move: " + this.getName());
            row = sc.nextInt();
            System.out.println("Enter the column for your move: " + this.getName());
            col = sc.nextInt();
        }
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
