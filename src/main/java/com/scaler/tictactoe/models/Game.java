package com.scaler.tictactoe.models;

import com.scaler.tictactoe.exception.DuplicateSymbolException;
import com.scaler.tictactoe.exception.InvalidBoardSizeException;
import com.scaler.tictactoe.exception.InvalidBotCountException;
import com.scaler.tictactoe.exception.InvalidNumberOfPlayersException;
import com.scaler.tictactoe.models.enums.GameStatus;
import com.scaler.tictactoe.models.enums.PlayerType;
import com.scaler.tictactoe.service.winningstrategy.WinningStrategy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Game {
    private Board currentBoard;
    private List<Player> players;
    private Player currentPlayer;
    private GameStatus gameStatus;
    private Player winner;
    private List<Move> moves;
    private List<Board> boardStates;
    private WinningStrategy winningStrategy;

    private Game(Board currentBoard, List<Player> players, WinningStrategy winningStrategy) {
        this.currentBoard = currentBoard;
        this.players = players;
        this.gameStatus = GameStatus.IN_PROGRESS;
        this.moves = new ArrayList<>();
        this.boardStates = new ArrayList<>();
        this.winningStrategy = winningStrategy;
    }

    public static class Builder {
        private int dimension;
        private List<Player> players;
        private WinningStrategy winningStrategy;

        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public Builder setWinningStrategy(WinningStrategy winningStrategy) {
            this.winningStrategy = winningStrategy;
            return this;
        }

        public void validateBotCount() throws InvalidBotCountException {
            int botCount = 0;
            for (Player player : players) {
                if (player.getPlayerType().equals(PlayerType.BOT)) {
                    botCount++;
                }
            }
            if (botCount > 1) {
                throw new InvalidBotCountException("Bot count can't be >1, current count: " + botCount);
            }
        }

        public void validateBoardSize() throws InvalidBoardSizeException {
            if (dimension < 3 || dimension > 10) {
                throw new InvalidBoardSizeException("Board size has to be >=3 and <=10, current size: " + dimension);
            }
        }

        public void validatePlayerNumber() throws InvalidNumberOfPlayersException {
            if (players.size() != dimension - 1) {
                throw new InvalidNumberOfPlayersException("Number of players is invalid, current player count: " + players.size());
            }
        }

        public void validateDuplicateSymbol() throws DuplicateSymbolException {
            HashSet<Character> symbolSet = new HashSet<>();
            for (Player player : players) {
                symbolSet.add(player.getSymbol());
            }
            if (symbolSet.size() != players.size()) {
                throw new DuplicateSymbolException("All players should have unique symbols");
            }
        }

        public void validate() throws InvalidBotCountException, InvalidBoardSizeException, DuplicateSymbolException, InvalidNumberOfPlayersException {
            validateBotCount();
            validateBoardSize();
            validatePlayerNumber();
            validateDuplicateSymbol();
        }

        public Game build() throws InvalidBotCountException, DuplicateSymbolException, InvalidNumberOfPlayersException, InvalidBoardSizeException {
            validate();
            return new Game(new Board(dimension), players, winningStrategy);
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
