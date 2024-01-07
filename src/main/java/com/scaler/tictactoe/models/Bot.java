package com.scaler.tictactoe.models;

import com.scaler.tictactoe.exception.GameOverException;
import com.scaler.tictactoe.models.enums.BotDifficultyLevel;
import com.scaler.tictactoe.models.enums.PlayerType;
import com.scaler.tictactoe.service.botplayingstrategy.BotPlayingStrategy;
import com.scaler.tictactoe.service.botplayingstrategy.BotPlayingStrategyFactory;

public class Bot extends Player {
    private BotDifficultyLevel botDifficultyLevel;

    public Bot(int id, String name, char symbol, PlayerType playerType, BotDifficultyLevel botDifficultyLevel) {
        super(id, name, symbol, playerType);
        this.botDifficultyLevel = botDifficultyLevel;
    }

    @Override
    public Move makeMove(Board board) throws GameOverException {
        BotPlayingStrategy botPlayingStrategy = BotPlayingStrategyFactory.getBotPlayingStrategy();
        return botPlayingStrategy.makeMove(board, this);
    }
}
