package com.scaler.tictactoe.service.botplayingstrategy;

import com.scaler.tictactoe.exception.GameOverException;
import com.scaler.tictactoe.models.Board;
import com.scaler.tictactoe.models.Cell;
import com.scaler.tictactoe.models.Move;
import com.scaler.tictactoe.models.enums.CellState;

import java.util.List;

public class RandomBotPlayingStrategy implements BotPlayingStrategy {
    @Override
    public Move makeMove(Board board) throws GameOverException {
        List<List<Cell>> matrix = board.getBoard();
        for (int i = 0; i < matrix.size(); i++) {
            for (int j = 0; j < matrix.size(); j++) {
                if (matrix.get(i).get(j).getCellState().equals(CellState.EMPTY)) {
                    return new Move(i, j);
                }
            }
        }
        //if no empty cells are found, then game is over
        throw new GameOverException("No new cells to play here, Game Over");
    }
}
