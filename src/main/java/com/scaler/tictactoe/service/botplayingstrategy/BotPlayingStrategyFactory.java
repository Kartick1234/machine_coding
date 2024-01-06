package com.scaler.tictactoe.service.botplayingstrategy;

public class BotPlayingStrategyFactory {
    public BotPlayingStrategy getBotPlayingStrategy() {
        return new RandomBotPlayingStrategy();
    }
}
