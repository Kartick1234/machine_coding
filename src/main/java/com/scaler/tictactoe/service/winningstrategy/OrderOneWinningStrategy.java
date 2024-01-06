package com.scaler.tictactoe.service.winningstrategy;

import com.scaler.tictactoe.models.Board;
import com.scaler.tictactoe.models.Move;
import com.scaler.tictactoe.models.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderOneWinningStrategy implements WinningStrategy {

    private int dimension;
    private List<HashMap<Character, Integer>> rowHashMaps;
    private List<HashMap<Character, Integer>> colHashMaps;
    private HashMap<Character, Integer> topLeftHashMaps;
    private HashMap<Character, Integer> topRightHashMaps;
    private HashMap<Character, Integer> cornerHashMaps;

    public OrderOneWinningStrategy(int dimension) {
        this.dimension = dimension;
        //Initialization of HashMap
        rowHashMaps = new ArrayList<>();
        colHashMaps = new ArrayList<>();
        topLeftHashMaps = new HashMap<>();
        topRightHashMaps = new HashMap<>();
        cornerHashMaps = new HashMap<>();
        for (int i = 0; i < dimension; i++) {
            rowHashMaps.add(new HashMap<>());
            colHashMaps.add(new HashMap<>());
        }
    }

    @Override
    public Player checkWinner(Board board, Move lastMove) {
        Player player = lastMove.getPlayer();
        char symbol = player.getSymbol();
        int row = lastMove.getCell().getRow();
        int col = lastMove.getCell().getCol();
        if (checkRowWin(row, symbol)) {
            return player;
        } else if (checkColumnWin(col, symbol)) {
            return player;
        } else if (isTopLeftDiagonalCell(row, col) && checkTopLeftDiagonalWin(symbol)) {
            return player;
        } else if (isTopRightDiagonalCell(row, col) && checkTopRightDiagonalWin(symbol)) {
            return player;
        } else if (isCornerCell(row, col) && checkCornerWin(symbol)) {
            return player;
        }
        return null;
    }

    private boolean isTopLeftDiagonalCell(int row, int col) {
        return row == col;
    }

    private boolean isTopRightDiagonalCell(int row, int col) {
        return (row + col) == (dimension - 1);
    }

    private boolean isCornerCell(int row, int col) {
        if (row == 0 || row == dimension - 1) {
            return col == 0 || col == dimension - 1;
        }
        return false;
    }

    private boolean checkRowWin(int row, char symbol) {
        //if the symbol is not existing, insert in the map
        rowHashMaps.get(row).putIfAbsent(symbol, 0);
        //for every insertion of symbol, update the count
        rowHashMaps.get(row).put(symbol, rowHashMaps.get(row).get(symbol) + 1);
        return rowHashMaps.get(row).get(symbol) == dimension;
    }

    private boolean checkColumnWin(int col, char symbol) {
        //if the symbol is not existing, insert in the map
        colHashMaps.get(col).putIfAbsent(symbol, 0);
        //for every insertion of symbol, update the count
        colHashMaps.get(col).put(symbol, colHashMaps.get(col).get(symbol) + 1);
        return colHashMaps.get(col).get(symbol) == dimension;
    }

    private boolean checkTopRightDiagonalWin(char symbol) {
        //if the symbol is not existing, insert in the map
        topRightHashMaps.putIfAbsent(symbol, 0);
        //for every insertion of symbol, update the count
        topRightHashMaps.put(symbol, topRightHashMaps.get(symbol) + 1);
        return topRightHashMaps.get(symbol) == dimension;
    }

    private boolean checkTopLeftDiagonalWin(char symbol) {
        //if the symbol is not existing, insert in the map
        topLeftHashMaps.putIfAbsent(symbol, 0);
        //for every insertion of symbol, update the count
        topLeftHashMaps.put(symbol, topLeftHashMaps.get(symbol) + 1);
        return topLeftHashMaps.get(symbol) == dimension;
    }

    private boolean checkCornerWin(char symbol) {
        //if the symbol is not existing, insert in the map
        cornerHashMaps.putIfAbsent(symbol, 0);
        //for every insertion of symbol, update the count
        cornerHashMaps.put(symbol, cornerHashMaps.get(symbol) + 1);
        return cornerHashMaps.get(symbol) == 4;
    }
}
