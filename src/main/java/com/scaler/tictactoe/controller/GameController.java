package com.scaler.tictactoe.controller;

import com.scaler.tictactoe.models.Game;
import com.scaler.tictactoe.models.Player;
import com.scaler.tictactoe.models.enums.GameStatus;
import com.scaler.tictactoe.models.enums.WinningStrategies;
import com.scaler.tictactoe.service.winningstrategy.WinningStrategyFactory;

import java.util.List;

public class GameController {
    public Game createGame(int dimension, List<Player> players, WinningStrategies winningStrategies) {
        try {
            return Game.builder()
                    .setDimension(dimension)
                    .setPlayers(players)
                    .setWinningStrategy(WinningStrategyFactory.getWinningStrategy(winningStrategies, dimension))
                    .build();
        } catch (Exception e) {
            System.out.println("Error is: " + e.getMessage());
            System.out.println("Could not start the game, something has to be modified");
        }
        return null;
    }
    public void displayBoard(Game game){
        game.getCurrentBoard().printBoard();
    }
    public GameStatus getGameStatus(Game game){
        return game.getGameStatus();
    }
    public Player getGameWinner(Game game){
        return game.getWinner();
    }
}
