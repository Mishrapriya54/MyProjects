package com.priyam.guessnumberrestservice.controller;

import com.priyam.guessnumberrestservice.model.Games;
import com.priyam.guessnumberrestservice.model.Rounds;
import java.time.LocalDateTime;
import java.util.Scanner;

public class UserInput {

    public String getGuess() {

        Scanner readGuess = new Scanner(System.in);

        String regex = "\\d+";
        System.out.println("Please input a 4 digit number");
        String userGuess = readGuess.nextLine();
        while (true) {
            if (userGuess.length() == 4 && userGuess.matches(regex)) {

                break;
            } else {
                System.out.println("please input a valid 4 digit number");
                userGuess = readGuess.nextLine();
            }
        }
        return userGuess;
    }

    public int getGameId() {
        System.out.println("Please Enter Game Id");
        Scanner readId = new Scanner(System.in);
        int gameId = readId.nextInt();
        return gameId;
    }

    public Games getNewGameInfo() {
        System.out.println("Please Enter Game Id");
        Scanner readId = new Scanner(System.in);
        int gameId = readId.nextInt();
        Games newGame = new Games();
        newGame.setGameId(gameId);
        newGame.setGameStatus("In Progress");
        return newGame;
    }

    Rounds getNewRoundInfo() {
        //LocalDateTime time=new LocalDateTime(System.currentTimeMillis());
        //Timestamp ts=new Timestamp(System.currentTimeMillis());
        LocalDateTime rightNow = LocalDateTime.now();
        String userGuess = getGuess();
        Rounds newRound = new Rounds();
        newRound.setUserGuess(userGuess);
        newRound.setTime(rightNow);
        return newRound;
    }

}
