package com.scaler.tictactoe.controller;

import com.scaler.tictactoe.exception.GameOverException;
import com.scaler.tictactoe.models.Board;
import com.scaler.tictactoe.models.Game;
import com.scaler.tictactoe.models.Move;
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

    public void displayBoard(Game game) {
        game.getCurrentBoard().printBoard();
    }

    public GameStatus getGameStatus(Game game) {
        return game.getGameStatus();
    }

    public Player getGameWinner(Game game) {
        return game.getWinner();
    }

    public void updateGameMoves(Game game, Move move) {
        game.getMoves().add(move);
    }

    public Move executeMove(Game game, Player player) throws GameOverException {
        Move move = player.makeMove(game.getCurrentBoard());
        game.setNumberOfSymbols(game.getNumberOfSymbols() + 1);
        updateGameStatus(game);
        updateGameMoves(game, move);
        updateBoardStates(game);
        return move;
    }

    private void updateGameStatus(Game game) {
        int numberOfSymbols = game.getNumberOfSymbols();
        int dimension = game.getCurrentBoard().getSize();
        if (numberOfSymbols == dimension * dimension) {
            System.out.println("Game is draw");
            game.setGameStatus(GameStatus.DRAW);
        }
    }

    private void updateBoardStates(Game game) {
        game.getBoardStates().add(new Board(game.getCurrentBoard()));
    }

    public Player checkWinner(Game game, Move lastPlayedMove) {
        Player player = game.getWinningStrategy().checkWinner(game.getCurrentBoard(), lastPlayedMove);
        if (player != null) {
            game.setWinner(player);
            game.setGameStatus(GameStatus.COMPLETED);
            return player;
        }
        return null;
    }
}
