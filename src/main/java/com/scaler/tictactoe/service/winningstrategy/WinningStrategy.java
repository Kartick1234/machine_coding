package com.scaler.tictactoe.service.winningstrategy;

import com.scaler.tictactoe.models.Board;
import com.scaler.tictactoe.models.Move;
import com.scaler.tictactoe.models.Player;

public interface WinningStrategy {
    Player checkWinner(Board board, Move lastMove);
}
