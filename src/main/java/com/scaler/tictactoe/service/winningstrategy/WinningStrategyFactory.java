package com.scaler.tictactoe.service.winningstrategy;

import com.scaler.tictactoe.models.enums.WinningStrategies;

public class WinningStrategyFactory {
    public WinningStrategy getWinningStrategy(WinningStrategies winningStrategies, int dimension) {
        //add a switch case on basis of the strategy chosen and return the object
        return new OrderOneWinningStrategy(dimension);
    }
}
