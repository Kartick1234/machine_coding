package com.scaler.tictactoe.service.botplayingstrategy;

import com.scaler.tictactoe.exception.GameOverException;
import com.scaler.tictactoe.models.Board;
import com.scaler.tictactoe.models.Move;
import com.scaler.tictactoe.models.Player;

public interface BotPlayingStrategy {
    Move makeMove(Board board, Player player) throws GameOverException;
}
