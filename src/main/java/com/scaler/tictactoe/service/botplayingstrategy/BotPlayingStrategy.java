package com.scaler.tictactoe.service.botplayingstrategy;

import com.scaler.tictactoe.exception.GameOverException;
import com.scaler.tictactoe.models.Board;
import com.scaler.tictactoe.models.Move;

public interface BotPlayingStrategy {
    Move makeMove(Board board) throws GameOverException;
}
