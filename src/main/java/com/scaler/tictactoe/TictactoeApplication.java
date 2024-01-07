package com.scaler.tictactoe;

import com.scaler.tictactoe.controller.GameController;
import com.scaler.tictactoe.exception.GameOverException;
import com.scaler.tictactoe.models.Bot;
import com.scaler.tictactoe.models.Game;
import com.scaler.tictactoe.models.Move;
import com.scaler.tictactoe.models.Player;
import com.scaler.tictactoe.models.enums.BotDifficultyLevel;
import com.scaler.tictactoe.models.enums.GameStatus;
import com.scaler.tictactoe.models.enums.PlayerType;
import com.scaler.tictactoe.models.enums.WinningStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class TictactoeApplication {

    public static void main(String[] args) throws GameOverException {
        SpringApplication.run(TictactoeApplication.class, args);
        Scanner sc = new Scanner(System.in);
        GameController gameController = new GameController();
        System.out.println("Enter the dimension of the game");
        int dimension = sc.nextInt();
        System.out.println("Will there be any bot? Y/N");
        String isBotPresent = sc.next();
        List<Player> players = new ArrayList<>();
        int iteratorNumber = dimension - 1;
        if (isBotPresent.equalsIgnoreCase("Y")) {
            iteratorNumber = iteratorNumber - 1;
            System.out.println("Name of the bot: ");
            String botName = sc.next();
            System.out.println("Symbol of the bot: ");
            String botSymbol = sc.next();
            BotDifficultyLevel botDifficultyLevel = BotDifficultyLevel.EASY;
            Bot bot = new Bot(dimension, botName, botSymbol.charAt(0), PlayerType.BOT, botDifficultyLevel);
            players.add(bot);
        }
        for (int i = 1; i <= iteratorNumber; i++) {
            System.out.println("Name of the player: " + i);
            String playerName = sc.next();
            System.out.println("Symbol of the player: " + i);
            String symbol = sc.next();
            players.add(new Player(i, playerName, symbol.charAt(0), PlayerType.HUMAN));
        }
        Collections.shuffle(players);
        Game game = gameController.createGame(dimension, players, WinningStrategies.ORDER_ONE_WINNING_STRATEGY);
        int playerIndex = -1;
        while (gameController.getGameStatus(game).equals(GameStatus.IN_PROGRESS)) {
            System.out.println("Current board status");
            gameController.displayBoard(game);
            playerIndex++;
            playerIndex = playerIndex % players.size();//looping with the same order
            Move movePlayed = gameController.executeMove(game, players.get(playerIndex));
            //Write a logic to add undo feature
            Player winner = gameController.checkWinner(game, movePlayed);
            if (winner != null) {
                System.out.println("Winner is: " + winner.getName());
                break;
            }
        }
        System.out.println("Final board status");
        gameController.displayBoard(game);
    }

}
